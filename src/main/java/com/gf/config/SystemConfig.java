package com.gf.config;

import com.gf.message.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: SystemConfig</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-09 17:49
 */
@Configuration
public class SystemConfig {

    public final static String queueName = "spring-boot";

    @Bean
    Queue queue(){
        return new Queue( queueName , false );
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange( "spring-boot-exchange" );
    }

    @Bean
    Binding binding(Queue queue , TopicExchange exchange){
        return BindingBuilder.bind( queue ).to( exchange ).with( queueName );
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory , MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory( connectionFactory );
        container.setQueueNames( queueName );
        container.setMessageListener( listenerAdapter );

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter( receiver , "receiveMessage" );
    }
}
