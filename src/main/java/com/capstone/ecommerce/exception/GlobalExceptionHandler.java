package com.capstone.ecommerce.exception;

import com.capstone.ecommerce.exception.userExceptions.UserDoesNotHaveARoleException;
import com.capstone.ecommerce.exception.userExceptions.UserPasswordAndEmailDontMatchException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@NoArgsConstructor
@AllArgsConstructor
public class GlobalExceptionHandler extends RuntimeException{
    @Getter @Setter
    private String errorMessage;

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity alreadyExistsHandler(AlreadyExistsException alreadyExistsException) {
        return new ResponseEntity(alreadyExistsException.getMsg(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity notFoundHandler(NotFoundException notFoundException){
        return new ResponseEntity(notFoundException.getMsg(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DatabaseEmptyException.class)
    public ResponseEntity noValuesInDatabase(DatabaseEmptyException databaseEmptyException){
        return new ResponseEntity("There are no values to display", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserDoesNotHaveARoleException.class)
    public ResponseEntity userDoesHaveARoleException(){
        return new ResponseEntity("This user has not been assigned a role", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserPasswordAndEmailDontMatchException.class)
    public ResponseEntity userPasswordAndEmailDontMatchException(UserPasswordAndEmailDontMatchException userPasswordAndEmailDontMatchException){
        return new ResponseEntity("Email and Password do not match", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity invalidInputException(InvalidInputException invalidInputException){
        return new ResponseEntity(invalidInputException.getMsg(),HttpStatus.BAD_REQUEST);
    }

}

