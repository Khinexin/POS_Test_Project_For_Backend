package com.mytest.pos.exception;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = -664652635849844265L;

	public InvalidInputException(String message) {
        super(message);
    }
}