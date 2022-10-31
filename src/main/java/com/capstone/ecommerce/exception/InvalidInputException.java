package com.capstone.ecommerce.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InvalidInputException extends RuntimeException{

    private String msg;

    public InvalidInputException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
