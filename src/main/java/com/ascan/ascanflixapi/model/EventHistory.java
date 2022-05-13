package com.ascan.ascanflixapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class EventHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type; // trocar por enum como

    @OneToOne
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Status subscription;

    private LocalDateTime created_at;

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

    public Status getSubscription() {
        return subscription;
    }

    public void setSubscription(Status subscription) {
        this.subscription = subscription;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
