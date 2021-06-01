package com.fei.projetodecantadorbe.responseAPI;

import lombok.Data;

@Data
public class WarningResponse {

    private String code;
    private String message;
    private String type = "info";
    
    public WarningResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}