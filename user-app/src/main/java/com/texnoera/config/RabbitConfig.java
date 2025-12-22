package com.texnoera.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "user_exchange";
    public static final String QUEUE_NAME = "user_queue";
    public static final String ROUTING_KEY = "user_routing_key";

    @Bean
    DirectExchange exchangeUser() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queueUser() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    Binding bindingUser() {
        return BindingBuilder
                .bind(queueUser())
                .to(exchangeUser())
                .with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter converter) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }

}
