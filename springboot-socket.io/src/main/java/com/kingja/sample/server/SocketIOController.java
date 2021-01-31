package com.kingja.sample.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:TODO
 * Create Time:2021/1/31 0031 16:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@RestController
@RequestMapping("/api/socket.io")
public class SocketIOController {

    @Autowired
    private ISocketIOService socketIOService;

    @PostMapping(value = "/pushMessageToUser", produces = Constants.CONTENT_TYPE)
    public Object pushMessageToUser(@RequestParam String userId, @RequestParam String msgContent) {
        socketIOService.pushMessageToUser(userId, msgContent);
        return "ok";
    }



}