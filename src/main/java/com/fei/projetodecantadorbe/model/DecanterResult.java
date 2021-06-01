package com.fei.projetodecantadorbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DecanterResult {

    private float glicerina;
    private float etanol;
    private float solucaoLavagem;

}
