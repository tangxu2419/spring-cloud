package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicLong;

import static com.config.RabbitMQChannel.RABBITMQ_DIRECT_INPUT_CHANNEL;
import static com.config.RabbitMQChannel.RABBITMQ_FANOUT_INPUT_CHANNEL;
import static com.config.RabbitMQChannel.RABBITMQ_TOPIC_INPUT_CHANNEL;

/**
 * @author tangxu
 */
@Component
@EnableBinding(RabbitMQChannel.class)
public class RabbitMQMonitor {

    private final static Logger log = LoggerFactory.getLogger(RabbitMQMonitor.class);

    private static final Integer MAX_WAIT_COUNT = 20;
    private AtomicLong messageCount = new AtomicLong();

    protected Long countUp() {
        return messageCount.incrementAndGet();
    }

    protected Long countDown() {
        return messageCount.decrementAndGet();
    }

    @StreamListener(RABBITMQ_DIRECT_INPUT_CHANNEL)
    public void directProcess(Message<String> message) {
        countUp();
        log.info("消费者[DIRECT]消费信息：{}",message.getPayload());
        countDown();
    }

    @StreamListener(RABBITMQ_FANOUT_INPUT_CHANNEL)
    public void fanoutProcess1(Message<String> message) {
        countUp();
        log.info("消费者[FANOUT-1]消费信息：{}",message.getPayload());
        countDown();
    }

    @StreamListener(RABBITMQ_FANOUT_INPUT_CHANNEL)
    public void fanoutProcess2(Message<String> message) {
        countUp();
        log.info("消费者[FANOUT-2]消费信息：{}",message.getPayload());
        countDown();
    }

    @StreamListener(RABBITMQ_TOPIC_INPUT_CHANNEL)
    public void topicProcess(Message<String> message) {
        countUp();
        log.info("消费者[TOPIC]消费信息：{}",message.getPayload());
        countDown();
    }

    @PreDestroy
    private void tearDown() throws InterruptedException {
        int waitCount = 0;
        while (messageCount.get() > 0 && waitCount++ < MAX_WAIT_COUNT) {
            log.info("正在关闭消息监听程序{}，等待3秒[{}/{}]...", this.getClass().getCanonicalName(), waitCount, MAX_WAIT_COUNT);
            Thread.sleep(3000L);
        }
        if (messageCount.get() > 0) {
            log.warn("应用非安全关闭，当前仍有{}条正在处理的消息", messageCount.get());
        }
    }

}
