package com.fei.projetodecantadorbe.service.componentes.services;

import com.fei.projetodecantadorbe.service.async.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimuladorService {

    private AsyncService asyncService;

    @Autowired
    public SimuladorService(AsyncService asyncService) {

        this.asyncService = asyncService;
    }

    public void updateMain() {
        this.asyncService.runAsyncRequests();
    }
}