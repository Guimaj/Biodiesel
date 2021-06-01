package com.fei.projetodecantadorbe.service.async;

import com.fei.projetodecantadorbe.service.componentes.services.NaEtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NaEtServiceAsync implements AsyncRequest{

    @Autowired
    private NaEtService naEtService;

    @Override
    public CompletableFuture<Void> asyncRequest() throws InterruptedException {
        while (true) {
            this.naEtService.sendToQueue();
            Thread.sleep(1000);
        }
    }
}
