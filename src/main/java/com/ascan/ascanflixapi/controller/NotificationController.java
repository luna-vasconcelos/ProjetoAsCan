package com.ascan.ascanflixapi.controller;

import com.ascan.ascanflixapi.constants.RabbitMQconstants;
import com.ascan.ascanflixapi.dto.NotificationDto;
import com.ascan.ascanflixapi.services.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping (value = "notification") // mapeia a classe através de um endpoint // endpoint notification
public class NotificationController {

    @Autowired
    private RabbitmqService rabbitmqService;

//    @Autowired
//    private UserRepository userRepository;

    @PutMapping
    private ResponseEntity alteraSubscription(
            @RequestBody
                    NotificationDto notification
                    //int id adicionar isso no RabbitMessage depois e passar tudo de uma vez
                )
            {
                this.rabbitmqService.enviaMensagem(RabbitMQconstants.queue_notification, notification);

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

                return new ResponseEntity(HttpStatus.OK);


        // adicionar nesse mesmo método, um outro que irá irá consultar a conta dele pelo id
        // se ele já tiver conta ou adicionar uma nova conta com novo id (que é como se fosse
        // o nome do usuário + o nome completo se não encontrar o id inicial.

        // outro método setando o tipo e o status aqui de acordo com qual notificação for

    }
}
