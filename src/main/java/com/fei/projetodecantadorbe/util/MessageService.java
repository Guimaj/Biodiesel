package com.fei.projetodecantadorbe.util;

import com.fei.projetodecantadorbe.enumerator.MessageValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(MessageValidation message){
        return message.getMessage();
    }

    public String createMessage(MessageValidation message, Object ...args){
        return messageSource.getMessage(message.getMessage(), args, LocaleContextHolder.getLocale());
    }

}
