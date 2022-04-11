package com.ascan.rabbitdockerascan.controller;

import com.ascan.rabbitdockerascan.constants.RabbitMQconstants;
import com.ascan.rabbitdockerascan.dto.SubscriptionsDto;
import com.ascan.rabbitdockerascan.services.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //classe api rest
@RequestMapping (value = "subscription")// mapeia a classe através de um endpoint // endpoint subscription
public class SubscriptionController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity alteraSubscription(@RequestBody SubscriptionsDto subscriptionsDto){
        this.rabbitmqService.enviaMensagem(RabbitMQconstants.fila_subscription, subscriptionsDto);
        return new ResponseEntity(HttpStatus.OK); //Apenas retornando OK se der certo o nosso método
    }
}
