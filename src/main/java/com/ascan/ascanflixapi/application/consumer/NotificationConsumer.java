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
            eventHistory.setSubscription_id(subscription.getId());
            eventHistoryRepository.save(eventHistory);

            System.out.print("Subscription Purchased successfully!");
        }
    }

        // Aí com o EventHistory salvo, fazer:
        //   - findbyId a Subscription_id pelo SubscriptionRepository
        //   - Procurar o status pelo status_id dessa Subscription
        //   - Identificar qual tipo de alteração é pelo type do EventHistpry que já tá salvo
        //   - Dá um updated no campo de update
        //   - Alterar ou Status para o novo status com base em qual type é
        //   - Salvar essa alteração
        //   - Mostrar uma mensagem de "Status da sua Inscrição salvo com sucesso!" ou algo assim
}
