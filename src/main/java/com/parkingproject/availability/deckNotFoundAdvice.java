package com.parkingproject.availability;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class deckNotFoundAdvice
{
    @ResponseBody
    @ExceptionHandler(deckNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String deckNotFoundHandler(deckNotFoundException ex)
    {
        return ex.getMessage();
    }
}
