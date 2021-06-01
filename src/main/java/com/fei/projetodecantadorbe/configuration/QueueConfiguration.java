package com.fei.projetodecantadorbe.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

    @Value("${queue.oil.name}")
    private String oilQueue;
    @Value("${queue.decanter.name}")
    private String decanterQueue;
    @Value("${queue.reactor.name}")
    private String reactorQueue;
    @Value("${queue.naEt.name}")
    private String naEtQueue;


    @Bean
    public Queue oilQueue() {
        return new Queue(oilQueue, true);
    }

    @Bean
    public Queue decanterQueue() {
        return new Queue(decanterQueue, true);
    }

    @Bean
    public Queue reactorQueue() {
        return new Queue(reactorQueue, true);
    }

    @Bean
    public Queue NaEtTank() {
        return new Queue(naEtQueue, true);
    }
}
