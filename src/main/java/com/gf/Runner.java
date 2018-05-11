package com.gf;

import com.gf.config.SystemConfig;
import com.gf.message.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: Runner</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-09 18:00
 */
@Component
public class Runner implements CommandLineRunner{

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;


    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver, ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message ...");
        rabbitTemplate.convertAndSend( SystemConfig.queueName , "Hello from RabbitMQ!" );
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }
}
