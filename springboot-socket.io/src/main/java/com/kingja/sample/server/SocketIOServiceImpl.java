package com.kingja.sample.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.MultiTypeArgs;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.MultiTypeEventListener;
import com.kingja.sample.DataDTO;

import org.assertj.core.util.DateUtil;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2021/1/31 0031 16:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Slf4j
@Service(value = "socketIOService")
public class SocketIOServiceImpl implements ISocketIOService {

    /**
     * 存放已连接的客户端
     */
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    /**
     * 自定义事件`push_data_event`,用于服务端与客户端通信
     */
    private static final String PUSH_DATA_EVENT = "push_data_event";
    private static final String PUSH_MULIT_DATA_EVENT = "push_mulit_data_event";
    private static final String PUSH_OBJECT_EVENT = "push_object_event";

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动
     */
    @PostConstruct
    private void autoStartup() {
        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     */
    @PreDestroy
    private void autoStop() {
        stop();
    }

    @Override
    public void start() {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            log.info("************ 客户端： " + getIpByClient(client) + " 已连接 ************");
            // 自定义事件`connected` -> 与客户端通信  （也可以使用内置事件，如：Socket.EVENT_CONNECT）
            client.sendEvent("connected", "你成功连接上了哦...");
            String userId = getParamsByClient(client);
            if (userId != null) {
                clientMap.put(userId, client);
            }
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String clientIp = getIpByClient(client);
            log.info(clientIp + " *********************** " + "客户端已断开连接");
            String userId = getParamsByClient(client);
            if (userId != null) {
                clientMap.remove(userId);
                client.disconnect();
            }
        });

        // 自定义事件`client_info_event` -> 监听客户端消息
        socketIOServer.addEventListener(PUSH_DATA_EVENT,
                String.class,
                new DataListener<String>() {
                    @Override
                    public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                        // 客户端推送`client_info_event`事件时，onData接受数据，这里是string类型的json数据，还可以为Byte[],object其他类型
                        String clientIp = SocketIOServiceImpl.this.getIpByClient(client);
                        log.info(clientIp + " ************ 客户端 但数据：" + data);
                    }
                });

//        socketIOServer.addEventListener(PUSH_MULIT_DATA_EVENT,
//                String.class,
//                new DataListener<String>() {
//                    @Override
//                    public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
//                        // 客户端推送`client_info_event`事件时，onData接受数据，这里是string类型的json数据，还可以为Byte[],object其他类型
//                        String clientIp = SocketIOServiceImpl.this.getIpByClient(client);
////                        ackSender.sendAckData("收到了");
//                        log.info(clientIp + " ************ 客户端 多数据1：" + data);
//                    }
//                });
        socketIOServer.addMultiTypeEventListener(PUSH_MULIT_DATA_EVENT, new MultiTypeEventListener() {
            @Override
            public void onData(SocketIOClient client, MultiTypeArgs data, AckRequest ackSender) throws Exception {
                // 客户端推送`client_info_event`事件时，onData接受数据，这里是string类型的json数据，还可以为Byte[],object其他类型
                String clientIp = SocketIOServiceImpl.this.getIpByClient(client);
//                        ackSender.sendAckData("收到了");
                log.info(clientIp + " ************ 客户端 多数据：" + data.get(0)+ data.get(1)+ data.get(2));

            }
        },String.class,String.class,Integer.class);

        socketIOServer.addEventListener(PUSH_OBJECT_EVENT,
                DataDTO.class,
                new DataListener<DataDTO>() {
                    @Override
                    public void onData(SocketIOClient client, DataDTO data, AckRequest ackSender) throws Exception {
                        // 客户端推送`client_info_event`事件时，onData接受数据，这里是string类型的json数据，还可以为Byte[],object其他类型
                        String clientIp = SocketIOServiceImpl.this.getIpByClient(client);
                        System.out.println(data);
                        log.info(clientIp + " ************ 客户端 传递对象：" + data.toString());
                    }
                });

        // 启动服务
        socketIOServer.start();

        // broadcast: 默认是向所有的socket连接进行广播，但是不包括发送者自身，如果自己也打算接收消息的话，需要给自己单独发送。
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    // 每3秒发送一次广播消息
                    Thread.sleep(5000);
                    socketIOServer.getBroadcastOperations().sendEvent("myBroadcast", "广播消息 " + DateUtil.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    // 每3秒发送一次广播消息
                    Thread.sleep(5000);
                    socketIOServer.getBroadcastOperations().sendEvent("push_mulit_data_event", "数据1 ", "数据2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public void pushMessageToUser(String userId, String msgContent) {
        SocketIOClient client = clientMap.get(userId);
        if (client != null) {
            client.sendEvent(PUSH_DATA_EVENT, msgContent);
        }
    }

    /**
     * 获取客户端url中的userId参数（这里根据个人需求和客户端对应修改即可）
     *
     * @param client: 客户端
     * @return: java.lang.String
     */
    private String getParamsByClient(SocketIOClient client) {
        // 获取客户端url参数（这里的userId是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> userIdList = params.get("userId");
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }

    /**
     * 获取连接的客户端ip地址
     *
     * @param client: 客户端
     * @return: java.lang.String
     */
    private String getIpByClient(SocketIOClient client) {
        String sa = client.getRemoteAddress().toString();
        String clientIp = sa.substring(1, sa.indexOf(":"));
        return clientIp;
    }

}