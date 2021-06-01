package com.fei.projetodecantadorbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class RequestParam {

    private HeaderParam header;

}