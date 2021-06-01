package com.fei.projetodecantadorbe.enumerator;

import lombok.Getter;

@Getter
public enum MessageValidation {

    DOCUMENT_DOCUMENT_ID("DUP001", "upload.data.document.documentId", true),
    DOCUMENT_NAME("DUP002", "upload.data.document.name", true),
    DOCUMENT_PDF_SIZE("DUP004", "upload.data.document.size", true),
    DOCUMENT_TYPE("DUP003", "upload.data.document.type", true),
    DOCUMENT_PDF_BYTES("DUP004", "upload.data.document.PDFBytes", true),

    UPLOAD_TYPE("DUPT004", "upload.data.document.upload.type", true),
    UPLOAD_TYPE_OBJ("DUPT004", "upload.data.document.upload.object.type", false),

    UPLOAD_DOCUMENT_OBJ("DUPT004", "upload.data.document.upload.object.document", false),

    UPLOAD_PEP_CPF_IS_REQUIRED("DUPP005", "upload.data.document.upload.pep", false),
    UPLOAD_PEP_DOCUMENT_TYPE_IS_REQUIRED("DUPP006", "upload.data.document.upload.insert.type.pep",
            false),
    UPLOAD_PEP_MAX_NUMBER("DUPP007", "upload.data.document.upload.pep.insert.max", false);

    private String code;
    private String message;
    private Boolean hasArgs = false;

    MessageValidation(String code, String message, boolean hasArgs) {
        this.code = code;
        this.message = message;
        this.hasArgs = hasArgs;
    }

}
