package com.dalstackoverflow.backendserver.controllers;

import com.dalstackoverflow.backendserver.services.RegistrationService;
import com.dalstackoverflow.backendserver.models.Registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ritva Katrodiya
 * This is the primary controller class of the Registration api.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("user/add")
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    private final RegistrationService registrationService;

    /**
     * This is RegistrationController Constructor
     * @param registrationService is used to initialize instance of RegistrationService
     */
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * This method is used to register user in DB
     * @param user is the object Registration model
     * @throws NoSuchAlgorithmException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void userRegister(@RequestBody Registration user) throws NoSuchAlgorithmException {
        LOGGER.info("Request Object:" + user.toString());

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        String encryptedPassword = sb.toString();
        user.setPassword(encryptedPassword);

        registrationService.postUser((Registration) user);
    }
}
