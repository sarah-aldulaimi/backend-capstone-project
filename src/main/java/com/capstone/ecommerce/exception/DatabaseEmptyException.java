package com.capstone.ecommerce.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DatabaseEmptyException extends RuntimeException{
    private String message;

    public DatabaseEmptyException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
