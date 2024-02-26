package com.example.vente_miel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyUsedException extends Exception{

    public EmailAlreadyUsedException(String message)
    {
        super(message);
    }
}
