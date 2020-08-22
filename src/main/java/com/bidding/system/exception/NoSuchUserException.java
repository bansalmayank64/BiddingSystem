package com.bidding.system.exception;

public class NoSuchUserException extends Exception {

	private static final long serialVersionUID = -4038219964615028412L;

	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

}
