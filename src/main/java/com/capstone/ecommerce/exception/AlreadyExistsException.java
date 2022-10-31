package com.capstone.ecommerce.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AlreadyExistsException extends RuntimeException {
    @Getter @Setter
    private String msg;
    public AlreadyExistsException(String message) {
        super(message);
        this.msg = message;
    }
}
