package com.fei.projetodecantadorbe.queue.NaEt;

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

@Slf4j
@Service
public class NaEtConsumer implements Consumer {

    @Autowired
    private ReactorService reactorService;

    @RabbitListener(queues = {"${queue.naEt.name}"})
    @Override
    public void receive(@Payload String fileBody, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
        log.info("combustivel recebido: " + fileBody);
        this.reactorService.sendToQueue(fileBody);
    }
}
