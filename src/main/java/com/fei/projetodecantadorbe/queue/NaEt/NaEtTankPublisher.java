package com.fei.projetodecantadorbe.queue.NaEt;

import com.fei.projetodecantadorbe.model.composto.NaEtTankFuel;
import com.fei.projetodecantadorbe.queue.Publisher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class NaEtTankPublisher implements Publisher<NaEtTankFuel> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Qualifier("NaEtTank")
    @Autowired
    private Queue queue;

    @Override
    public void send(NaEtTankFuel fuel) {

        Gson gson = new Gson();
        Type type = new TypeToken<NaEtTankFuel>() {}.getType();
        String json = gson.toJson(fuel, type);

        this.rabbitTemplate.convertAndSend(this.queue.getName(), json);
    }
}
