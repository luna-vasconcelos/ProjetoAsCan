package com.ascan.ascanflixapi.connections;

import com.ascan.ascanflixapi.constants.RabbitMQconstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component // notação que fala para o spring que essa classe será gerenciada pelo próprio spring
public class RabbitMQconnection {
    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQconnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    //está função é executada assim que nossa classe é instanciada pelo Spring, devido a anotação @Component
    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila(RabbitMQconstants.queue_notification);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);

        //Criando as filas no RabbitMQ
        this.amqpAdmin.declareQueue(filaEstoque);
        //Instanciando a exchange
        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
    }
}