package com.dalstackoverflow.backendserver.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.dalstackoverflow.backendserver.models.LoginRequest;
import com.dalstackoverflow.backendserver.models.LoginResponse;
import com.dalstackoverflow.backendserver.models.Registration;
import com.dalstackoverflow.backendserver.repositories.LoginRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository=mock(LoginRepository.class);

    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        loginService = new LoginService(loginRepository);
    }

    @Test
    public void testLoginUserWithValidCredentials() {
        Registration registration = new Registration();
        registration.setUserId(1);
        registration.setUserName("test");
        registration.setPassword("password");
        when(loginRepository.findByUserNameAndPassword("test", "password")).thenReturn(registration);

        LoginRequest loginRequest = new LoginRequest("test","password");
        loginRequest.setUsername("test");
        loginRequest.setPassword("password");
        LoginResponse result = loginService.loginUser(loginRequest);
        assertEquals("Login successful!", result.getMessage());
        assertEquals(1, (int) result.getUserId());
        assertEquals("test", result.getUserName());
        assertEquals("password", result.getPassword());
    }

    @Test
    public void testLoginUserWithInvalidCredentials() {
        when(loginRepository.findByUserNameAndPassword("test", "password")).thenReturn(null);

        LoginRequest loginRequest = new LoginRequest("test","password");
        loginRequest.setUsername("test");
        loginRequest.setPassword("password");
        LoginResponse result = loginService.loginUser(loginRequest);
        assertEquals("Invalid credentials. Please try again.", result.getMessage());
        assertEquals(null, result.getUserId());
        assertEquals(null, result.getUserName());
        assertEquals(null, result.getPassword());
    }

    @Test
    public void testGetUsernameWithValidUserId() {
        Registration registration = new Registration();
        registration.setUserId(1);
        registration.setUserName("test");
        registration.setPassword("password");
        when(loginRepository.findById(1)).thenReturn(registration);

        String result = loginService.getUsername(1);
        assertEquals("test", result);
    }

    @Test
    public void testGetUsernameWithInvalidUserId() {
        when(loginRepository.findById(1)).thenReturn(null);
        String result = loginService.getUsername(1);
        assertEquals(null, result);
    }
}
