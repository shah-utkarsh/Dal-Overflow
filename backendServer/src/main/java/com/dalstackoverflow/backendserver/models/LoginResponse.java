package com.dalstackoverflow.backendserver.models;
/**
 * @author Ritva Katrodiya
 * This is the model class for LoginResponse .
 */
public class LoginResponse {
    private String message;
    private Integer userId;

    private String userName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public LoginResponse(String message, Integer userId, String userName,String password) {
        this.message = message;
        this.userId = userId;
        this.userName = userName;
        this.password=password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                message + '\'' +
                userId +
                userName + password +
                '}';
    }
}
