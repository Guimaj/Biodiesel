package com.fei.projetodecantadorbe.queue.oil;

import com.fei.projetodecantadorbe.model.Oleo;
import com.fei.projetodecantadorbe.queue.Publisher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class OilPublisher implements Publisher<Oleo> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Qualifier("oilQueue")
    @Autowired
    private Queue queue;

    @Override
    public void send(Oleo oleo) {

        Gson gson = new Gson();
        Type type = new TypeToken<Oleo>() {}.getType();
        String json = gson.toJson(oleo, type);

        this.rabbitTemplate.convertAndSend(this.queue.getName(), json);
    }
}
