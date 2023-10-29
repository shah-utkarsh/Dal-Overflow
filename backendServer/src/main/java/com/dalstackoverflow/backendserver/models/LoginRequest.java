package com.dalstackoverflow.backendserver.models;

/**
 * @author Ritva Katrodiya
 * This is the model class for LoginRequest.
 */
public class LoginRequest {
    private String userName;
    private String password;

    public LoginRequest(String username, String password) {
        this.userName = username;
        this.password = password;
    }


    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
