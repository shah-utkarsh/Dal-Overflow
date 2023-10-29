package com.dalstackoverflow.backendserver.controllers;

import com.dalstackoverflow.backendserver.models.LoginRequest;
import com.dalstackoverflow.backendserver.models.LoginResponse;
import com.dalstackoverflow.backendserver.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ritva Katrodiya
 * This is the primary controller class of the Login api.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("user/login")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;

    /**
     *
     * This is LoginController Constructor
     * @param loginService is used to initialize instance of LoginService
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     *
     * @param loginRequest is an instance of LoginRequest Model class
     * @return it will return userId if Login successful.
     * @throws Exception when userId or password is invalid
     */
    @PostMapping
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) throws Exception {
        LOGGER.info("Calling Login Service");
        LOGGER.info("Request Object:" + loginRequest.toString());
        String encryptedPassword = getEncrypted(loginRequest.getPassword());
        loginRequest.setPassword(encryptedPassword);
        LoginResponse response = loginService.loginUser(loginRequest);

        if (response.getMessage().equals("Login successful!")) {
            int userId = response.getUserId();
            String userName = response.getUserName();
            String password = response.getPassword();
            response.setMessage("Login successful! Your user ID is " + userId +   userName  + password);
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }


    /**
     * This method is used to getch the username from DB
     * @param userId
     * @return userName when login successful
     */

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUsername(@PathVariable int userId) {
        String username = loginService.getUsername(userId);
        if (username != null)
        {
            return ResponseEntity.ok(username);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *
     * @param password is the raw password got from UI
     * @return will return encrypted password using SHA-256 algorithm
     */
    static String getEncrypted(String password) {
        if(password!=null) {
            String encryptedPassword = null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (byte b : hash) {
                    sb.append(String.format("%02x", b));
                }
                encryptedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return encryptedPassword;
        }
        else {
            return null;
        }
    }
}
