package com.joao.linktracer.config;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.joao.linktracer.config.exceptions.InvalidLinkException;
import com.joao.linktracer.controller.form.LinkForm;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidacaoHandler {


    private final MessageSource messageSource;

    public ErroValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDto> handleValidacao(MethodArgumentNotValidException exception){
        List<ErroDeFormularioDto> dto = new ArrayList<>();

        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
        fieldErros.forEach(e ->{
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
            dto.add(erro);
        });

        return dto;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidLinkException.class)
    public String handleInvalidLink(InvalidLinkException e) {
        return e.getMessage();
    }

}
