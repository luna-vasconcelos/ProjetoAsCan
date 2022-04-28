package com.ascan.rabbitdockerascan.consumer;

import com.ascan.rabbitdockerascan.constants.RabbitMQconstants;
import com.ascan.rabbitdockerascan.dto.SubscriptionsDto;
import com.ascan.rabbitdockerascan.models.SubscriptionModel;
import com.ascan.rabbitdockerascan.repository.SubscriptionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConsumer {
//TODO: implementar get(all)
    @Autowired //cria a implementação da interface automaticamente
    private SubscriptionRepository repository;

    @RabbitListener(queues = RabbitMQconstants.fila_subscription)
    private void consumer(SubscriptionsDto subscriptionsDto){
        System.out.println(subscriptionsDto.subscription);
        System.out.println("-----------------------------------");

        SubscriptionModel model = new SubscriptionModel();
        repository.save(model);
    }
}
