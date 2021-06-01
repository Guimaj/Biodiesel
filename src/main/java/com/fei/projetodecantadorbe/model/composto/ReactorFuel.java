package com.fei.projetodecantadorbe.model.composto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ReactorFuel {

    private float glycerin;
    private float ethanol;
    private float washSolution;
}
