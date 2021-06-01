package com.fei.projetodecantadorbe.queue;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

public interface Consumer {

    void receive(@Payload String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException;
}
