package com.dalstackoverflow.backendserver.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseTest {

    @Test
    public void testGetMessage() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        assertEquals("success", loginResponse.getMessage());
    }

    @Test
    public void testGetUserId() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        assertEquals(1, loginResponse.getUserId());
    }

    @Test
    public void testGetUserName() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        assertEquals("testUser", loginResponse.getUserName());
    }
    @Test
    public void testGetPassword() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        assertEquals("password", loginResponse.getPassword());
    }

    @Test
    public void testSetMessage() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        loginResponse.setMessage("failure");
        assertEquals("failure", loginResponse.getMessage());
    }

    @Test
    public void testSetUserId() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        loginResponse.setUserId(2);
        assertEquals(2, loginResponse.getUserId());
    }

    @Test
    public void testSetUserName() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        loginResponse.setUserName("testUser");
        assertEquals("testUser", loginResponse.getUserName());
    }
    @Test
    public void testSetPassword() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        loginResponse.setPassword("newPassword");
        assertEquals("newPassword", loginResponse.getPassword());
    }

    @Test
    public void testToString() {
        LoginResponse loginResponse = new LoginResponse("success", 1, "testUser", "password");
        String expectedString = "LoginResponse{success'1testUserpassword}";
        assertEquals(expectedString, loginResponse.toString());
    }

}
