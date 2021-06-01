package com.fei.projetodecantadorbe.service.async;

import com.fei.projetodecantadorbe.service.componentes.services.OleoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OilServiceAsync implements AsyncRequest{

    @Autowired
    private OleoService oleoService;

    @Override
    public CompletableFuture<Void> asyncRequest() throws InterruptedException {
        while (true){
            this.oleoService.sendToQueue();
            Thread.sleep(10000);
        }

    }
}
