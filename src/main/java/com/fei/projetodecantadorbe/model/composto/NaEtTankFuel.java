package com.fei.projetodecantadorbe.model.composto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaEtTankFuel {

    private Etanol etanol;
    private Sódio sodio;
    private Integer id;
}
