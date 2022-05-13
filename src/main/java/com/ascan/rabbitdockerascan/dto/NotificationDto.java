package com.ascan.rabbitdockerascan.dto;

import java.io.Serializable;

public class NotificationDto implements Serializable {
    public String notification; //tentar trocar por um enum depois
    // Trocar pela Rabbit Message mesmo construindo como objeto e depois passando pra string
}
