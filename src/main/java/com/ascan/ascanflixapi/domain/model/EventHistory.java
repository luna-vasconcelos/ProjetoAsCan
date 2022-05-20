package com.ascan.ascanflixapi.domain.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
public class EventHistory implements Serializable {

    private static final Integer serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String type; // trocar por enum

    @OneToOne
    private Subscription subscription_id;

    @CreationTimestamp
    private Instant created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Subscription getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(Subscription subscription_id) {
        this.subscription_id = subscription_id;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }
}
