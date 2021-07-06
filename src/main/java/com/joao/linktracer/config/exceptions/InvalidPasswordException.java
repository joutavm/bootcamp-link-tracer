package com.joao.linktracer.config.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String wrong_password) {
        super(wrong_password);
    }
}
