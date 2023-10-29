package com.dalstackoverflow.backendserver.services;

import com.dalstackoverflow.backendserver.models.Registration;
import com.dalstackoverflow.backendserver.repositories.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RegistrationServiceTest {

    private RegistrationService registrationService;

    @Mock
    private RegistrationRepository registrationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationService = new RegistrationService(registrationRepository);
    }

    @Test
    public void testPostUser() {
        Registration user = new Registration();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        user.setEmailId("testuser@test.com");

        registrationService.postUser(user);

        verify(registrationRepository, times(1)).save(user);
    }
}
