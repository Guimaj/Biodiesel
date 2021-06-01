package com.fei.projetodecantadorbe.queue.decanter;

import com.fei.projetodecantadorbe.queue.Consumer;
import com.fei.projetodecantadorbe.service.componentes.services.DecanterService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DecanterConsumer implements Consumer {

    @Autowired
    private DecanterService decanterService;

    @RabbitListener(queues = {"${queue.decanter.name}"})
    @Override
    public void receive(String fileBody, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
        log.info("Composto do reator recebido: " + fileBody);
        if(decanterService.getTotalVolume() <= 10){
            this.decanterService.receiveFromQueue(fileBody);
        }
    }
}
