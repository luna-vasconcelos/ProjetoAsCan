package com.ascan.ascanflixapi.application.consumer;

import com.ascan.ascanflixapi.connections.RabbitMQconnection;
import com.ascan.ascanflixapi.dto.NotificationDto;
import com.ascan.ascanflixapi.repository.SubscriptionRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationConsumer {
//TODO: implementar get(all)

//    @Autowired
//    private EventHistoryRepository eventHistoryRepository;

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

//    @RabbitListener(queues = RabbitMQconnection.QUEUE_GENERIC_NAME)
//    public void receiveMessage(NotificationDto notification) {
//        log.info("Received message as a generic AMQP 'Message' wrapper: {}", notification.toString());
//    }

    @RabbitListener(queues = RabbitMQconnection.QUEUE_SPECIFIC_NAME)
    public void receiveMessage2(final NotificationDto notification) {
        log.info("Received message and deserialized: {}", notification.toString());

//        if (Objects.equals(notification.notification, "SUBSCRIPTION_PURCHASED")){
//
//            SubscriptionRepository repository = new;
//            repository.save(notification.user_id);
//        }
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



//        if (Objects.equals(notification.notification, "SUBSCRIPTION_PURCHASED")){
//            EventHistory eventHistory_type = new EventHistory();
//            String type = "Assinatura Comprada";
//            eventHistory_type.setType(type);
//            eventHistoryRepository.save(eventHistory_type);
//        }

    //}

    // Fluxo Subscription Purchased
    // Adicionando procura/adição do usuário no banco
    //User userModel = new User();
    // Tentativa de colocar o findbyId em um método mas tá dando erro, não sei porque
//                public Optional<User> searchForId (int id){
//                    userRepository.findById(id);
//                    return ResponseEntity.ok(HttpStatus.OK);
//                }

    // se id encontrado
    //  print(usuário encontrado)
    //
    //userRepository.findById(id);

    // adicionar nesse mesmo método, um outro que irá irá consultar a conta dele pelo id
    // se ele já tiver conta ou adicionar uma nova conta com novo id (que é como se fosse
    // o nome do usuário + o nome completo se não encontrar o id inicial.

    // outro método setando o tipo e o status aqui de acordo com qual notificação for
}
