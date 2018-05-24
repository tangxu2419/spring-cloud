package com.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @author tangxu
 */
@Component
public interface RabbitMQChannel {

    String RABBITMQ_DIRECT_INPUT_CHANNEL = "direct_input_channel";
    String RABBITMQ_FANOUT_INPUT_CHANNEL = "fanout_input_channel";
    String RABBITMQ_TOPIC_INPUT_CHANNEL = "topic_input_channel";

    @Input(RABBITMQ_DIRECT_INPUT_CHANNEL)
    MessageChannel inputDirect();

    @Input(RABBITMQ_FANOUT_INPUT_CHANNEL)
    MessageChannel inputMultiple();

    @Input(RABBITMQ_TOPIC_INPUT_CHANNEL)
    MessageChannel inputTopic();

}
