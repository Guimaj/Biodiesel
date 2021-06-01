package com.fei.projetodecantadorbe.util;

import com.fei.projetodecantadorbe.enumerator.ApplicationEnum;
import com.fei.projetodecantadorbe.enumerator.MessageValidation;
import com.fei.projetodecantadorbe.enumerator.ValidationMessageEnum;
import com.fei.projetodecantadorbe.model.RequestParam;
import com.fei.projetodecantadorbe.responseAPI.ApiErrorResponse;
import com.fei.projetodecantadorbe.responseAPI.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseMessage {

    @Autowired
    private MessageService messageService;

    public void addBusinessErrorMessage(MessageValidation messageValidation, ApiErrorResponse response,
                                        RequestParam req, String documentNumber){
        String code = messageValidation.getCode();
        if(messageValidation.getHasArgs()) {
            String message = messageService.createMessage(messageValidation,
                    "", "(" + documentNumber + ")");
            response.getErrors().add(new ErrorResponse(code, message));
        }else{
            String message = messageService.createMessage(messageValidation);
            response.getErrors().add(new ErrorResponse(code, message));
        }
    }

    public static void addErrorMessage(ValidationMessageEnum validationMessageEnum, ApiErrorResponse response) {
        response.getErrors().add(new ErrorResponse(validationMessageEnum.getDomainData().getCode(),
                validationMessageEnum.getDomainData().getDescription()));
    }

    /**
     * chekc for error or not
     * @return void
     */
    public ResponseEntity<ApiErrorResponse> checkErrorOrNot(String status, ApiErrorResponse response){

        ResponseMessage responseMessage = new ResponseMessage();

        /** Status e retorno **/
        if(response.getErrors().isEmpty()){
            response.setCode("200");
            response.setMessage("Success");
            return ResponseEntity.status(200).body(response);
        }else{
            if("400".equals(status)){
                return responseMessage.topDoDeco(ApplicationEnum.HDI_BAD_REQUEST, status, response);
            } else if("401".equals(status)){
                return responseMessage.topDoDeco(ApplicationEnum.HDI_NOT_AUTHORIZED, status, response);
            }else if("404".equals(status)){
                return responseMessage.topDoDeco(ApplicationEnum.HDI_NOT_FOUND, status, response);
            }else if("453".equals(status)){
                return responseMessage.topDoDeco(ApplicationEnum.HDI_BUSINESS_EXCEPTION, status, response);
            }
        }
        return responseMessage.topDoDeco(ApplicationEnum.HDI_NOT_FOUND, status, response);
    }

    public ResponseEntity<ApiErrorResponse> topDoDeco(ApplicationEnum enumDoDeco, String code, ApiErrorResponse response){
        response.setCode(String.valueOf(enumDoDeco.getId()));
        response.setDescription(enumDoDeco.getDescription());
        response.setMessage(enumDoDeco.getMessage());
        return ResponseEntity.status(Integer.valueOf(code)).body(response);
    }

    public void responseClientApi (Integer statusCode, ApiErrorResponse response){
        if (statusCode == 400){
            this.addErrorMessage(ValidationMessageEnum.BAD_REQUEST, response);
        } else if (statusCode == 404) {
            this.addErrorMessage(ValidationMessageEnum.NOT_FOUND, response);
        } else if (statusCode == 401) {
            this.addErrorMessage(ValidationMessageEnum.UNAUTHORIZED, response);
        } else if (statusCode == 453){
            this.addErrorMessage(ValidationMessageEnum.UNAUTHORIZED, response);
        }else if (statusCode == 500) {
            this.addErrorMessage(ValidationMessageEnum.INTERNAL_ERROR, response);
        } else {
            this.addErrorMessage(ValidationMessageEnum.INTERNAL_ERROR, response);
        }
    }

}
