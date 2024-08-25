package com.soccerapi.soccerapi.controllers;

import com.soccerapi.soccerapi.exceptions.PlayerDoesNotExistException;
import com.soccerapi.soccerapi.models.entity.Player;
import com.soccerapi.soccerapi.models.errors.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExceptionHandlingControllerTest {



    private ExceptionHandlingController subject;


    @BeforeEach
    void setUp() {
        subject = new ExceptionHandlingController();

    }

    @Test
    void handlePlayerDoesNotExistException(){
        PlayerDoesNotExistException playerDoesNotExistException = new PlayerDoesNotExistException("Player does not exist.");
        ResponseEntity<ErrorResponse> expectedResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                .message("Player does not exist.")
                .build(), HttpStatus.NOT_FOUND);

        ResponseEntity<ErrorResponse> actualResponseEntity = subject.handlePlayerDoesNotExistException(playerDoesNotExistException);

        Assertions.assertEquals(expectedResponseEntity, actualResponseEntity);

    }

    @Test
    void handleMethodArgumentNotValidException(){
        Object[] objArr = new Object[1];
        objArr[0] = "Missing field active";
        BindingResult bindingResult = new BeanPropertyBindingResult(null,"name");
        bindingResult.addError(new FieldError("name","name","is mandatory"));
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException((MethodParameter) null, bindingResult);
        ResponseEntity<ErrorResponse> actualErrorResponse = subject.handleMethodArgumentNotValidException(methodArgumentNotValidException);

        List<String> errorList = new ArrayList<>();
        errorList.add("name is mandatory");

        ResponseEntity<ErrorResponse> expectedResponseEntity = new ResponseEntity<>(ErrorResponse.builder()
                .errorList(errorList)
                .build(), HttpStatus.BAD_REQUEST);

        Assertions.assertEquals(expectedResponseEntity, actualErrorResponse);
    }

}