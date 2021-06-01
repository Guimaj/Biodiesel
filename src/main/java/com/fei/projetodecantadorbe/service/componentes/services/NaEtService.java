package com.fei.projetodecantadorbe.service.componentes.services;

import com.fei.projetodecantadorbe.model.composto.NaEtTankFuel;
import com.fei.projetodecantadorbe.model.composto.Etanol;
import com.fei.projetodecantadorbe.model.composto.Sódio;
import com.fei.projetodecantadorbe.queue.NaEt.NaEtTankPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NaEtService implements ComponentService<NaEtTankFuel>{

    @Autowired
    private NaEtTankPublisher publisher;

    @Override
    public NaEtTankFuel createComponent() {
        Sódio sodio = new Sódio(0.25,1);
        Etanol etanol = new Etanol(0.125,1);

        return new NaEtTankFuel(etanol,sodio,2);
    }

    public void sendToQueue(){
        NaEtTankFuel fuel = this.createComponent();
        this.publisher.send(fuel);
        log.info("Combustivel publicado: {}",fuel.toString());
    }
}
