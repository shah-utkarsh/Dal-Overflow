package com.dalstackoverflow.backendserver.controllers;
import com.dalstackoverflow.backendserver.models.Registration;
import com.dalstackoverflow.backendserver.services.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RegistrationService registrationService;

    private RegistrationController registrationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        registrationController = new RegistrationController(registrationService);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    void userRegisterSuccess() throws Exception {
        Registration user = new Registration();
        user.setUserName("testUser");
        user.setPassword("password");

        doNothing().when(registrationService).postUser(any(Registration.class));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"username\": \"testUser\",\n" +
                                "  \"password\": \"password\"\n" +
                                "}"))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();
    }
}
