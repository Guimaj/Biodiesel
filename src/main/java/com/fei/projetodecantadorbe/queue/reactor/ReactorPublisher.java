package com.fei.projetodecantadorbe.queue.reactor;

import com.fei.projetodecantadorbe.queue.Publisher;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class ReactorPublisher implements Publisher<String> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Qualifier("reactorQueue")
    @Autowired
    private Queue queue;

    @Override
    public void send(String payload) {

        this.rabbitTemplate.convertAndSend(this.queue.getName(), payload);
    }
}
