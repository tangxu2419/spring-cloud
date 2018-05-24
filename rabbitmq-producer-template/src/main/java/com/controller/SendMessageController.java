package com.controller;

import com.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangxu
 */
@RestController
public class SendMessageController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/send/direct")
    public ResponseEntity sendDirectMessage(@RequestParam(value = "message") String message) {
        producerService.outputDirectMessage(message);
        return ResponseEntity.ok("调用成功，消息以发送");
    }

    @GetMapping("/send/fanout")
    public ResponseEntity sendFanoutMessage(@RequestParam(value = "message") String message) {
        producerService.outputFanoutMessage();
        return ResponseEntity.ok("调用成功，消息以发送");
    }

    @GetMapping("/send/topic")
    public ResponseEntity sendTopicMessage(@RequestParam(value = "message") String message) {
        producerService.outputTopicMessage(message);
        return ResponseEntity.ok("调用成功，消息以发送");
    }

}

