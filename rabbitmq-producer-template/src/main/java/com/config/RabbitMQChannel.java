package com.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @author tangxu
 */
@Component
public interface RabbitMQChannel {

    String RABBIT_DIRECT_OUTPUT_CHANNEL = "direct_output_channel";
    String RABBIT_FANOUT_OUTPUT_CHANNEL = "fanout_output_channel";
    String RABBIT_TOPIC_OUTPUT_CHANNEL = "topic_output_channel";

    @Output(RABBIT_DIRECT_OUTPUT_CHANNEL)
    MessageChannel outputDirect();

    @Output(RABBIT_FANOUT_OUTPUT_CHANNEL)
    MessageChannel outputFanout();

    @Output(RABBIT_TOPIC_OUTPUT_CHANNEL)
    MessageChannel outputTopic();
}
