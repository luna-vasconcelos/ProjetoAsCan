package com.ascan.ascanflixapi.application.consumer;

import com.ascan.ascanflixapi.connections.RabbitMQconnection;
import com.ascan.ascanflixapi.domain.model.Subscription;
import com.ascan.ascanflixapi.domain.model.User;
import com.ascan.ascanflixapi.dto.NotificationDto;
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

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    @RabbitListener(queues = RabbitMQconnection.QUEUE_SPECIFIC_NAME)
    public void receiveMessage2(final NotificationDto notification) {
        log.info("Received message and deserialized: {}", notification.toString());

        Optional<User> user = userRepository.findById(notification.user_id);

        // trocar para enum + switch-case
        if (Objects.equals(notification.notification, "SUBSCRIPTION_PURCHASED")){
            Subscription subscription = new Subscription();
            subscription.setUser(user.get());

            subscriptionRepository.save(subscription);
        }
    }

    //
//    // TODO: adicionar validação do evento
//    @RabbitListener(queues = RabbitMQconstants.queue_notification)
//    private void consumer(NotificationDto notification){
//        System.out.println(notification.toString()); // receber o eventhistory
//        System.out.println("-----------------------------------");

        // Passar na mensagem Rabbit o EventHistory:
        //   - Type (purchased, canceled ou restarted) (colocar como um enum)
        //   - Subscription_id (que nesse cenário seria pegue quando o usuário entra na aplicação
        //
        // Colocar tipo assim: EventHistory eventHistory = eventHistoryRepository.save(notification.notification);
        // Salvar o EventHistory
        // Aí com o objeto do EventHistpry salvo, fazer tipo isso:
        //   - findbyId a Subscription_id pelo SubscriptionRepository
        //   - Procurar o status pelo status_id dessa Subscription
        //   - Identificar qual tipo de alteração é pelo type do EventHistpry que já tá salvo
        //   - Dá um updated no campo de update
        //   - Alterar ou Status para o novo status com base em qual type é
        //   - Salvar essa alteração
        //   - Mostrar uma mensagem de "Status da sua Inscrição salvo com sucesso!" ou algo assim
}
