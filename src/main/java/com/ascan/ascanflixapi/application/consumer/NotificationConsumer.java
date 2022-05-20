package com.ascan.ascanflixapi.application.consumer;

import com.ascan.ascanflixapi.connections.RabbitMQconnection;
import com.ascan.ascanflixapi.domain.model.EventHistory;
import com.ascan.ascanflixapi.domain.model.Status;
import com.ascan.ascanflixapi.domain.model.Subscription;
import com.ascan.ascanflixapi.domain.model.User;
import com.ascan.ascanflixapi.dto.NotificationDto;
import com.ascan.ascanflixapi.repository.EventHistoryRepository;
import com.ascan.ascanflixapi.repository.StatusRepository;
import com.ascan.ascanflixapi.repository.SubscriptionRepository;
import com.ascan.ascanflixapi.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationConsumer {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private EventHistoryRepository eventHistoryRepository;

    Subscription subscription = new Subscription();
    Status status = new Status();
    EventHistory eventHistory = new EventHistory();

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    //TODO: adicionar validação do evento
    @RabbitListener(queues = RabbitMQconnection.QUEUE_SPECIFIC_NAME)
    public void receiveMessage(final NotificationDto notification) {
        log.info("Received message and deserialized: {}", notification.toString());

        Optional<User> user = userRepository.findById(notification.user_id);

        // trocar para enum + switch-case + método de cada tipo de Notificação de Subscription
        if (Objects.equals(notification.notification, "SUBSCRIPTION_PURCHASED")){
            // Operações na Tabela de Status
            Status newStatus = new Status();
            String status_name = "ativa";
            newStatus.setStatus_name(status_name);
            statusRepository.save(newStatus);

            // Operações na Tabela de Subscription
            subscription.setUser(user.get());
            subscription.setStatus_id(newStatus.getId());
            subscriptionRepository.save(subscription);

            // Operações na Tabela de EventHistoyry
            String typeSP = "SUBSCRIPTION_PURCHASED";
            eventHistory.setType(typeSP);
            eventHistory.setSubscription_id(validateSubscription(subscription.getId()));
            eventHistoryRepository.save(eventHistory);

            System.out.println("Subscription Purchased successfully!");
        }

        if (Objects.equals(notification.notification, "SUBSCRIPTION_CANCELED")){
            // Operações na Tabela de Subscription
            Optional<Subscription> updateSubscription = subscriptionRepository.
                    findByUser(validateUser(notification.user_id));
            subscriptionRepository.save(updateSubscription.get());

            // Operações na Tabela de Status
            String status_name = "cancelada";
            Status statusUpdate = validateStatus(updateSubscription.get().getStatus_id());
            statusUpdate.setStatus_name(status_name);
            statusRepository.save(statusUpdate);

            // Operações na Tabela de EventHistory
            String typeSP = "SUBSCRIPTION_CANCELED";
            eventHistory.setType(typeSP);
            eventHistory.setSubscription_id(validateSubscription(subscription.getId()));
            eventHistoryRepository.save(eventHistory);

            System.out.println("Subscription Canceled successfully!");
        }

        if (Objects.equals(notification.notification, "SUBSCRIPTION_RESTARTED")){
            // Operações na Tabela de Subscription
            Optional<Subscription> updateSubscription = subscriptionRepository.
                    findByUser(validateUser(notification.user_id));
            subscriptionRepository.save(updateSubscription.get());

            // Operações na Tabela de Status
            String status_name = "ativa";
            Status statusUpdate = validateStatus(updateSubscription.get().getStatus_id());
            statusUpdate.setStatus_name(status_name);
            statusRepository.save(statusUpdate);

            // Operações na Tabela de EventHistoyry
            String typeSP = "SUBSCRIPTION_RESTARTED";
            eventHistory.setType(typeSP);
            eventHistory.setSubscription_id(validateSubscription(subscription.getId()));
            eventHistoryRepository.save(eventHistory);

            System.out.println("Subscription Restarted successfully!");
        }
    }

    private User validateUser(Integer user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if(user.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return user.get();
    }

    private Status validateStatus(Integer status_id) {
        Optional<Status> status = statusRepository.findById(status_id);
        if(status.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return status.get();
    }

    private Subscription validateSubscription(Integer subscription_id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(subscription_id);
        if(subscription.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return subscription.get();
    }
}
