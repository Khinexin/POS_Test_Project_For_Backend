package com.mytest.pos.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 282436470542710342L;

	public DataNotFoundException(String message) {
        super(message);
    }

}