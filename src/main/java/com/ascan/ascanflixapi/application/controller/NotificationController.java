package com.ascan.ascanflixapi.application.controller;

import com.ascan.ascanflixapi.dto.NotificationDto;
import com.ascan.ascanflixapi.services.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping (value = "notification")
public class NotificationController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity alteraSubscription(@RequestBody NotificationDto notification)
        {this.rabbitmqService.sendMessage(notification);
                return new ResponseEntity(HttpStatus.OK);
        }
}
