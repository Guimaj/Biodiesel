package com.fei.projetodecantadorbe.controller;

import com.fei.projetodecantadorbe.model.HeaderParam;
import com.fei.projetodecantadorbe.service.componentes.services.SimuladorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimuladorController {

    @Autowired
    private SimuladorService service;

    @GetMapping(value = "/simulator", consumes = {"*"})
    public ResponseEntity<String> updateControllerValidation(
            @RequestHeader(value = "Content-Type", required = true) String contentType) throws Exception {
        final HeaderParam headers = HeaderParam.of(contentType);
        return ResponseEntity.ok("teste");
    }
}
