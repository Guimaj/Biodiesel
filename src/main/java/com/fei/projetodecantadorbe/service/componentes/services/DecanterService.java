package com.fei.projetodecantadorbe.service.componentes.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fei.projetodecantadorbe.model.componente.Biodiesel;
import com.fei.projetodecantadorbe.model.composto.Glicerina;
import com.fei.projetodecantadorbe.model.composto.ReactorFuel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DecanterService implements ComponentService<String> {

    private ReactorFuel fuel;
    private ObjectMapper mapper;
    private Biodiesel biodiesel;
    private Glicerina glicerina;
    private float releasedVolume = 3;
    private float totalVolume;

    @Autowired
    public DecanterService(ReactorFuel fuel, ObjectMapper mapper) {
        this.fuel = fuel;
        this.mapper = mapper;
        this.biodiesel = new Biodiesel();
        this.glicerina = new Glicerina();
    }

    @Override
    public String createComponent() {
        return null;
    }

    public void receiveFromQueue(String payload){
        try{
            JsonNode received = mapper.readTree(payload);

            float oil = received.get("oilVolume").floatValue();
            float ethanol = received.get("ethanolVolume").floatValue();
            float sodium = received.get("naOhVolume").floatValue();

            this.totalVolume = this.totalVolume + oil + ethanol + sodium;

            if(this.totalVolume >= 3){
                sendToDecanter();
            }

        }catch (Exception e){
            log.error("Erro no recebimento dos compostos do reator - {}",e.getMessage());
        }
    }

    private void sendToDecanter() throws InterruptedException {

        this.fuel.setEthanol((float) (this.releasedVolume * 0.09));
        this.fuel.setGlycerin((float) (this.releasedVolume * 0.02));
        this.fuel.setWashSolution((float) (this.releasedVolume * 0.89));

        this.totalVolume = this.totalVolume - this.releasedVolume;
        log.info("Volume restante no decantador: {}",this.totalVolume);

        Thread.sleep(5000);

        float washSolution = fuel.getWashSolution();

        for(int i = 0;i < 3;i++){
            washSolution = washSolution - (float) (washSolution * 0.075);
        }

        washSolution = washSolution - (float) (washSolution * 0.03);

        this.biodiesel.setTotalVolume(this.biodiesel.getTotalVolume() + washSolution);
        this.glicerina.setTotalVolume(this.glicerina.getTotalVolume() + this.fuel.getGlycerin());
        log.info("Quantidade total de biodiesel: " + biodiesel.getTotalVolume());
        log.info("Quantidade total de glicerina: " + glicerina.getTotalVolume());
    }

    public float getTotalVolume(){
        return this.totalVolume;
    }
}
