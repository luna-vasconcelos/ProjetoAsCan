package com.ascan.rabbitdockerascan.consumer;

import com.ascan.rabbitdockerascan.constants.RabbitMQconstants;
import com.ascan.rabbitdockerascan.dto.SubscriptionsDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConsumer {

    @RabbitListener(queues = RabbitMQconstants.fila_subscription)
    private void consumer(SubscriptionsDto subscriptionsDto){
        System.out.println(subscriptionsDto.subscription);
        System.out.println("-----------------------------------");
    }
}
