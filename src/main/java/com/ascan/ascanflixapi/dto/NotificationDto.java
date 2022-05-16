package com.ascan.ascanflixapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@JsonIgnoreProperties
public class NotificationDto implements Serializable {

    @NotBlank
    @JsonProperty("user_id")
    public Integer user_id;

    @NotBlank
    @JsonProperty("notification")
    public String notification;

    public NotificationDto() {

    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "user_id=" + user_id +
                ", notification='" + notification + '\'' +
                '}';
    }
}
