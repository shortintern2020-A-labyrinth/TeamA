package com.example.demo.controller;

import com.example.demo.Form.ChatForm;
import com.example.demo.service.EmologService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    EmologService service;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(ChatForm message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFromname(), message.getMessage(), time);
    }

    @MessageMapping(value = "/connect" )
    @SendTo(value = "/topic/messages") // 処理結果の送り先
    String connect(String name) {
        log.info("connect {}", name);
        return name + "さんが接続しました。";
    }


}