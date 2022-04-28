package com.ascan.rabbitdockerascan.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private enum name {
        ATIVA,
        CANCELADA
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
