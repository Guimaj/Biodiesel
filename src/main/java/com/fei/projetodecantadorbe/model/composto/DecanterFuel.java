package com.fei.projetodecantadorbe.model.composto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DecanterFuel {

    private float oilVolume;
    private float naOhVolume;
    private float ethanolVolume;

}
