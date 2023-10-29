package com.dalstackoverflow.backendserver.exception;

/**
 * This is an exception class to be used if a valid user session is not found.
 * This would be primarily thrown while validating user during question posting and answer posting.
 * @author Sreejith Nair
 */
public class InvalidUserException extends RuntimeException{
    String message;

    /**
     * This method will be called while throwing the exception.
     * @param message
     */
    public InvalidUserException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * @return Error message string
     */
    @Override
    public String getMessage() {
        return message;
    }
}

