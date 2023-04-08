package com.challenge.challenge.Errors;

public class NotFoundException extends RuntimeException{
        private static final long serialVersionUID = 502911347036058950L;
        public NotFoundException(String msg, String entityName) {
            super(entityName + ": "+msg);
        }
}
