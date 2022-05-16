package com.ascan.ascanflixapi.services;

import com.ascan.ascanflixapi.connections.RabbitMQconnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqService {

    private static final Logger log = LoggerFactory.getLogger(RabbitmqService.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitmqService(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Object message) {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMQconnection.EXCHANGE_NAME, RabbitMQconnection.ROUTING_KEY, message);
    }
}
