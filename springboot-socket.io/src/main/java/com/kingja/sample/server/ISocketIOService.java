package com.kingja.sample.server;

/**
 * Description:TODO
 * Create Time:2021/1/31 0031 16:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ISocketIOService {
    /**
     * 启动服务
     */
    void start();

    /**
     * 停止服务
     */
    void stop();

    /**
     * 推送信息给指定客户端
     *
     * @param userId:     客户端唯一标识
     * @param msgContent: 消息内容
     */
    void pushMessageToUser(String userId, String msgContent);
}
