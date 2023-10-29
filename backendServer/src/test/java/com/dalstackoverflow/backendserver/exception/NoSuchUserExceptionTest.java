package com.dalstackoverflow.backendserver.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoSuchUserExceptionTest {

    @Test
    public void testConstructor() {
        String message = "User not found";
        NoSuchUserException exception = new NoSuchUserException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testNoSuchUserException() {
        Assertions.assertThrows(NoSuchUserException.class, () -> {
            throw new NoSuchUserException("No such User found");
        });
    }
}