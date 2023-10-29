package com.dalstackoverflow.backendserver.controllers;

import com.dalstackoverflow.backendserver.models.LoginRequest;
import com.dalstackoverflow.backendserver.models.LoginResponse;
import com.dalstackoverflow.backendserver.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loginUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        LoginResponse loginResponse = new LoginResponse("Login successful!", 1, "username", "password");
        when(loginService.loginUser(loginRequest)).thenReturn(loginResponse);

        ResponseEntity<LoginResponse> responseEntity = loginController.loginUser(loginRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Login successful! Your user ID is 1usernamepassword", responseEntity.getBody().getMessage());
    }

    @Test
    void loginUserInvalidCredentials() throws Exception {
        LoginRequest loginRequest = new LoginRequest("invalidUsername", "invalidPassword");
        LoginResponse loginResponse = new LoginResponse("Invalid username or password", 0, "", "");
        when(loginService.loginUser(loginRequest)).thenReturn(loginResponse);

        ResponseEntity<LoginResponse> responseEntity = loginController.loginUser(loginRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid username or password", responseEntity.getBody().getMessage());
    }

    @Test
    void getUsername()
    {
        int userId = 1;
        String expectedUsername = "username";
        when(loginService.getUsername(userId)).thenReturn(expectedUsername);

        ResponseEntity<String> responseEntity = loginController.getUsername(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUsername, responseEntity.getBody());
    }
}
