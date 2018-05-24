package com;

import com.config.RabbitMQChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author tangxu
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableBinding(RabbitMQChannel.class)
public class RabbitMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class);
    }

}
