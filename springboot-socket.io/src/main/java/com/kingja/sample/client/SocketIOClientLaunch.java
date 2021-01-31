package com.kingja.sample.client;

import com.kingja.sample.DataDTO;
import com.kingja.sample.JSONUtil;

import org.assertj.core.util.DateUtil;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO
 * Create Time:2021/1/31 0031 16:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Slf4j
public class SocketIOClientLaunch {

    public static void main(String[] args) {
        // 服务端socket.io连接通信地址
        String url = "http://127.0.0.1:8888";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;
            // userId: 唯一标识 传给服务端存储
            final Socket socket = IO.socket(url + "?userId=1", options);

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args1) {
//                    socket.send("hello...");
                }
            });

            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on("connected", objects -> log.debug("服务端:" + objects[0].toString()));

            // 自定义事件`push_data_event` -> 接收服务端消息
            socket.on("push_data_event", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    log.debug("服务端:" + objects[0].toString());
                }
            });
            socket.on("push_mulit_data_event", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    log.debug("服务端:" + objects[0].toString()+ objects[1].toString());
                }
            });

            // 自定义事件`myBroadcast` -> 接收服务端广播消息
            socket.on("myBroadcast", objects -> log.debug("服务端：" + objects[0].toString()));

            socket.connect();

            while (true) {
                Thread.sleep(5000);
                // 自定义事件`push_data_event` -> 向服务端发送消息
                //1.可以直接发送JSONObject，用对应的对象(DataDTO)可以接受，注意要有无参构造方法
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type",1);
                jsonObject.put("message","jsonObject携带消息");
                //2.如果要传送对象，则先要转成JsonObject对象
                DataDTO dataDTO = new DataDTO();
                dataDTO.setType(2);
                dataDTO.setMessage("dataDTO携带消息");

//                socket.emit("push_data_event", "发送数据 " + DateUtil.now());
//                socket.emit("push_object_event", jsonObject);
//                socket.emit("push_object_event", JSONUtil.objectToJson(dataDTO));
                socket.emit("push_mulit_data_event", "多数据1 ","多数据2",3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}