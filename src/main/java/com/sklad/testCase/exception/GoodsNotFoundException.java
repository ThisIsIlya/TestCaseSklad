package com.sklad.testCase.exception;

public class GoodsNotFoundException extends RuntimeException{
    public GoodsNotFoundException(String message) {
        super(message);
    }
}
