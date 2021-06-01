package com.fei.projetodecantadorbe.enumerator;

public enum ApplicationEnum {

    /**
     * HDI http error status
     */
    HDI_BUSINESS_EXCEPTION(453, "Erro de negócio", "Business Exception"),
    HDI_BAD_REQUEST(400, "Dados inválidos", "Bad request"),
    HDI_NOT_AUTHORIZED(401, "Não autorizado","Unauthorized"),
    HDI_NOT_FOUND(404, "Dados não encontrados","NOT FOUND");

    Integer id;
    String message;
    String description;

    ApplicationEnum(Integer id, String message, String description) {
        this.id = id;
        this.message = message;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() { return message; }
}
