package com.dalstackoverflow.backendserver.exception;

/**
 * This is an exception class to be used for Login services
 * @author Sreejith Nair
 */
public class NoSuchUserException extends RuntimeException{
    String message;

    /**
     * This method will be called while throwing the exception.
     * @param message
     */
    public NoSuchUserException(String message) {
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
