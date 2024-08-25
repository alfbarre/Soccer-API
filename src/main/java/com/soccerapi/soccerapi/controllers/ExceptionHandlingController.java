package com.soccerapi.soccerapi.controllers;

import com.soccerapi.soccerapi.exceptions.PlayerDoesNotExistException;
import com.soccerapi.soccerapi.models.errors.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(PlayerDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handlePlayerDoesNotExistException(PlayerDoesNotExistException playerDoesNotExistException) {

        return new ResponseEntity<>(ErrorResponse.builder()
                .message(playerDoesNotExistException.getMessage())
                .build(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorList = new ArrayList<>();

        for(int i = 0; i < fieldErrors.size(); i++){
            errorList.add(fieldErrors.get(i).getField()+ " " + fieldErrors.get(i).getDefaultMessage());
        }

        return new ResponseEntity<>(ErrorResponse.builder()
                .errorList(errorList)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
