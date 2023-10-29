package com.dalstackoverflow.backendserver.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.dalstackoverflow.backendserver.models.Registration;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistrationRepositoryTest {

    @Mock
    private RegistrationRepository registrationRepository=mock(RegistrationRepository.class);

    @Test
    public void testFindByUserName() {
        Registration registration = new Registration();
        registration.setUserId(1);
        registration.setUserName("test");
        registration.setPassword("password");
        when(registrationRepository.findByUserName("test")).thenReturn(registration);
        Registration result = registrationRepository.findByUserName("test");
        assertEquals(registration, result);
    }
}
