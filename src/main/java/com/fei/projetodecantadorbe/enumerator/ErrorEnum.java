package com.fei.projetodecantadorbe.enumerator;

public enum ErrorEnum {

    HEADER_PARAMETERS_INCORRECT(1, "Enter the correct parameter to header!"),
    BODY_PARAMETERS_INCORRECT(2, "Enter the correct parameter to body!"),

    /**
     * HDI http error status
     */
    HDI_BUSINESS_EXCEPTION(452, "Business Exception"),
    HDI_INTERNAL_ERROR(552, "API Internal Error"),
    HDI_PROGRESS_COMMUNICATION_ERROR(553, "Communication error ocurred when progress service was called");

    Integer id;
    String description;

    ErrorEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
