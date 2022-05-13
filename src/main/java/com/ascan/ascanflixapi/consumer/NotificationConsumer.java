package com.ascan.ascanflixapi.consumer;

import com.ascan.ascanflixapi.constants.RabbitMQconstants;
import com.ascan.ascanflixapi.dto.NotificationDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
//TODO: implementar get(all)

//    @Autowired
//    private EventHistoryRepository eventHistoryRepository;

//    @Autowired
//    private NotificationDto notification;

    // TODO: adicionar validação do evento
    @RabbitListener(queues = RabbitMQconstants.queue_notification)
    private void consumer(NotificationDto notification){
        System.out.println(notification.notification); // receber o eventhistory
        System.out.println("-----------------------------------");

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

    }
}
