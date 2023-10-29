package com.dalstackoverflow.backendserver.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginRequestTest {

    @Test
    void testGettersAndSetters() {

        LoginRequest loginRequest = new LoginRequest("testUser", "testPassword");
        loginRequest.setUsername("newUser");
        loginRequest.setPassword("newPassword");
        assertEquals("newUser", loginRequest.getUsername());
        assertEquals("newPassword", loginRequest.getPassword());
    }

    @Test
    void testToString() {

        LoginRequest loginRequest = new LoginRequest("testUser", "testPassword");

        String result = loginRequest.toString();

        assertEquals("LoginRequest{username='testUser', password='testPassword'}", result);
    }

    @Test
    void testConstructor() {

        LoginRequest loginRequest = new LoginRequest("testUer", "testPassword");


        assertEquals("testUer", loginRequest.getUsername());
        assertEquals("testPassword", loginRequest.getPassword());
    }
}

