package com.ingressos.ingressos.domain.exceptions;

public class TicketsNotAvailableException extends RuntimeException {
    public TicketsNotAvailableException(String message){
        super(message);
    }
}
