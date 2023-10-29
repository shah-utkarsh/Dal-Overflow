package com.dalstackoverflow.backendserver.repositories;

import com.dalstackoverflow.backendserver.models.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ritva Katrodiya
 * This is the repository interface that will be used by the project for users.
 */
@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Integer> {

    /**
     * This method will fire on API call and return user based on userName.
     * @param username
     * @return usre object
     */
    Registration findByUserName(String username);
}
