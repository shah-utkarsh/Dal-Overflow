package com.dalstackoverflow.backendserver.repositories;
import com.dalstackoverflow.backendserver.models.Registration;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author Ritva Katrodiya
 * This is the repository interface that will be used by the project for users.
 */
@Repository
public interface LoginRepository extends CrudRepository<Registration, Integer>
{
    /**
     * This method will be used to fetch the User/Registration Object based on the username and password.
     * @param username
     * @param password
     * @return User object
     */
    Registration findByUserNameAndPassword(String username, String password);

    /**
     * This method will be used to fetch the User/Registration Object based on the user ID.
     * @param userID
     * @return User Object
     */
    Registration findById(int userID);

}

