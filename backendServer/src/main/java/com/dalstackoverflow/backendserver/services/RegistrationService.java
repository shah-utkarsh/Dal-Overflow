package com.dalstackoverflow.backendserver.services;

import com.dalstackoverflow.backendserver.repositories.RegistrationRepository;

import com.dalstackoverflow.backendserver.models.Registration;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ritva Katrodiya
 * This is the service class for posting and fetching user
 */
@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    /**
     * This  is the constructor of RegistrationService class
     * @param registrationRepository is instance of RegistrationRepository
     */
    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }


    /**
     * This method will be called on registration of user.
     * @param user1
     */
    @Transactional
    public void postUser(Registration user1) {
        registrationRepository.save(user1);
    }
}
