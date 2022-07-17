package com.gabrielffguimaraes.freteapi.controller;


import com.gabrielffguimaraes.freteapi.exceptions.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(messages);
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handlerResponseStatusException(ResponseStatusException e){
        String mensagemErro = e.getReason();
        HttpStatus codigoStatus = e.getStatus();
        ApiErrors apiErrors = new ApiErrors(mensagemErro); // CONVERTE PARA ARRAY DE STRINGS
        return new ResponseEntity(apiErrors,codigoStatus);
    }
}
