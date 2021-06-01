package com.fei.projetodecantadorbe.queue.decanter;

import com.fei.projetodecantadorbe.model.composto.DecanterFuel;
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
public class DecanterPublisher implements Publisher<DecanterFuel> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Qualifier("decanterQueue")
    @Autowired
    private Queue queue;

    @Override
    public void send(DecanterFuel fuel) {

        Gson gson = new Gson();
        Type type = new TypeToken<DecanterFuel>() {}.getType();
        String json = gson.toJson(fuel, type);

        this.rabbitTemplate.convertAndSend(this.queue.getName(), json);
    }
}
