package com.fei.projetodecantadorbe.service.componentes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fei.projetodecantadorbe.model.composto.DecanterFuel;
import com.fei.projetodecantadorbe.model.composto.NaEtTankFuel;
import com.fei.projetodecantadorbe.queue.decanter.DecanterPublisher;
import com.fei.projetodecantadorbe.queue.reactor.ReactorPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReactorService implements ComponentService<String>{

    private DecanterFuel combustivel;
    private ObjectMapper mapper;
    private DecanterPublisher decanterPublisher;
    private ReactorPublisher reactorPublisher;
    private int cycles = 0;

    @Autowired
    public ReactorService(ReactorPublisher reactorPublisher, DecanterFuel combustivel, ObjectMapper mapper, DecanterPublisher decanterPublisher){
        this.reactorPublisher = reactorPublisher;
        this.combustivel = combustivel;
        this.mapper = mapper;
        this.decanterPublisher = decanterPublisher;
    }

    @Override
    public String createComponent() {
        return "";
    }

    public void sendToQueue(String payload){
        this.reactorPublisher.send(payload);
        log.info("Composto publicado no reator: {}", payload);
    }

    public void receiveFromQueue(String payload) throws JsonProcessingException {

        this.cycles++;

        JsonNode obj = mapper.readTree(payload);

        if(obj.get("id").asInt() == 1){

            float volume = obj.get("volume").floatValue();

            this.combustivel.setOilVolume(this.combustivel.getOilVolume() + volume);

            log.info("Volume de óleo no reator: {}", this.combustivel.getOilVolume());
        }
        else{
            NaEtTankFuel combustivel = new ObjectMapper().readValue(obj.toString(),NaEtTankFuel.class);

            this.combustivel.setNaOhVolume((float) (this.combustivel.getNaOhVolume() + combustivel.getSodio().getVolume()));
            this.combustivel.setEthanolVolume((float) (this.combustivel.getEthanolVolume() + combustivel.getEtanol().getVolume()));

            log.info("Volume de sódio no reator: {}", this.combustivel.getNaOhVolume());
            log.info("Volume de etanol no reator: {}", this.combustivel.getEthanolVolume());
        }

        if(this.combustivel.getOilVolume() > 1.25 && this.combustivel.getEthanolVolume() > 2.5 && this.combustivel.getNaOhVolume() > 1.25){

            DecanterFuel combustivelToDecanter = new DecanterFuel(1.25f,1.25f,2.50f);

            this.decanterPublisher.send(combustivelToDecanter);

            combustivel.setOilVolume((float) (combustivel.getOilVolume() - 1.25));
            combustivel.setNaOhVolume((float) (combustivel.getNaOhVolume() - 1.25));
            combustivel.setEthanolVolume((float) (combustivel.getEthanolVolume() - 2.5));
        }

        log.info("ciclos executados até o momento: {}",this.cycles);
    }
}
