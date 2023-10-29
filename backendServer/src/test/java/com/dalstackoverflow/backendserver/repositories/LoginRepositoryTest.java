package com.dalstackoverflow.backendserver.repositories;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.dalstackoverflow.backendserver.models.Registration;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginRepositoryTest {

    @Mock
    private LoginRepository loginRepository=mock(LoginRepository.class);

    @Test
    public void testFindByUserNameAndPassword() {
        Registration registration = new Registration();
        registration.setUserId(1);
        registration.setUserName("test");
        registration.setPassword("password");
        when(loginRepository.findByUserNameAndPassword("test", "password")).thenReturn(registration);
        Registration result = loginRepository.findByUserNameAndPassword("test", "password");
        assertEquals(registration, result);
    }

    @Test
    public void testFindById() {
        Registration registration = new Registration();
        registration.setUserId(1);
        registration.setUserName("test");
        registration.setPassword("password");
        when(loginRepository.findById(1)).thenReturn(registration);
        Registration result = loginRepository.findById(1);
        assertEquals(registration, result);
    }
}
