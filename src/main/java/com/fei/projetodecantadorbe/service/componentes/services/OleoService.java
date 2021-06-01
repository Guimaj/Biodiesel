package com.fei.projetodecantadorbe.service.componentes.services;

import com.fei.projetodecantadorbe.model.Oleo;
import com.fei.projetodecantadorbe.queue.oil.OilPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OleoService implements ComponentService<Oleo>{

    @Autowired
    private OilPublisher publisher;

    @Override
    public Oleo createComponent() {
        return new Oleo((float) (1 + Math.random() * (2.0 - 1.0)),10,1);
    }

    public void sendToQueue(){
        Oleo oleo = this.createComponent();
        this.publisher.send(oleo);
        log.info("Óleo publicado: {}",oleo.toString());
    }
}
