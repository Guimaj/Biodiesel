package com.fei.projetodecantadorbe.queue.reactor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fei.projetodecantadorbe.queue.Consumer;
import com.fei.projetodecantadorbe.service.componentes.services.ReactorService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ReactorConsumer implements Consumer {

    @Autowired
    private ReactorService reactorService;

    @RabbitListener(queues = {"${queue.reactor.name}"})
    @Override
    public void receive(@Payload String fileBody, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        log.info("Composto do reator recebido: " + fileBody);
        try{
            //channel.basicAck(tag,false);
            this.reactorService.receiveFromQueue(fileBody);
        }catch (JsonProcessingException ex){
            log.error("Erro na conversao do objeto, reenviando documento a fila!");
            //channel.basicNack(tag,false,true);
        }
    }
}
