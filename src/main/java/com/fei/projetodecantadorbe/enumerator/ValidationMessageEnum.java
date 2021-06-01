package com.fei.projetodecantadorbe.enumerator;

public enum ValidationMessageEnum {

    UNAUTHORIZED("BFFAUTO001", "Não autorizado"),
    NOT_FOUND("BFFAUTO002", "Not found"),
    BAD_REQUEST("BFFAUTO003", "Parametros Inválidos"),
    INTERNAL_ERROR("BFFAUTO004", "Erro Interno");

    private DomainData domainData = new DomainData();

    private ValidationMessageEnum(String code, String description) {
        this.domainData.setCode(code);
        this.domainData.setDescription(description);
    }

    public DomainData getDomainData() {
        return domainData;
    }


}