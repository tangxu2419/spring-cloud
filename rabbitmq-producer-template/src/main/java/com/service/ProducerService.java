package com.service;

import com.config.RabbitMQChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author tangxu
 */
@Component
public class ProducerService {

    private final static Logger log = LoggerFactory.getLogger(ProducerService.class);

    private RabbitMQChannel directChannel;

    public ProducerService(RabbitMQChannel directChannel) {
        this.directChannel = directChannel;
    }

    public void outputDirectMessage(String queueInfo) {
        log.info("=======================[RabbitMQ: direct 队列]===============================");
        log.info("生产者[direct]发送信息：[{}]", queueInfo);
        Message<String> message = MessageBuilder.withPayload(queueInfo).build();
        directChannel.outputDirect().send(message);
        log.info("================================[结束]=======================================");
    }

    public void outputFanoutMessage() {
        log.info("=======================[RabbitMQ: FANOUT 队列]===============================");
        for (int i = 0; i < 10; i++) {
            String queueInfo = String.format("FANOUT messge[%s]", i);
            log.info("生产者[FANOUT]发送信息：[{}]", queueInfo);
            Message<String> message = MessageBuilder.withPayload(queueInfo).build();
            directChannel.outputFanout().send(message);
        }
        log.info("================================[结束]=======================================");
    }

    public void outputTopicMessage(String queueInfo) {
        log.info("=======================[RabbitMQ: topic 队列]===============================");
        log.info("生产者[topic]发送信息：[{}]", queueInfo);
        Message<String> message = MessageBuilder.withPayload(queueInfo).build();
        directChannel.outputTopic().send(message);
        log.info("================================[结束]=======================================");
    }
}
