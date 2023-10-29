package com.dalstackoverflow.backendserver.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InvalidUserExceptionTest {

    @Test
    public void testCreateInvalidUserException() {
        InvalidUserException exception = new InvalidUserException("Invalid User");
        assertNotNull(exception);
        assertEquals("Invalid User", exception.getMessage());
    }

    @Test
    public void testThrowInvalidUserException() {
        Assertions.assertThrows(InvalidUserException.class, () -> {
            throw new InvalidUserException("Invalid User");
        });
    }



}