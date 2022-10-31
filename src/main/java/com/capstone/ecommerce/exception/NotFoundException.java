package com.capstone.ecommerce.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class NotFoundException extends RuntimeException {
    @Getter @Setter
    private String msg;
    public NotFoundException(String message) {
        super(message);
        this.msg = message;
    }
}
