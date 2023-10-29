package com.dalstackoverflow.backendserver.models;

import jakarta.persistence.*;

/**
 * @author Ritva Katrodiya
 * This is the model class for Registration table.
 * The model class will be used for posting and fetching user details.
 */

@Entity
@Table(name = "user")
public class Registration {
    @Id

    @Column(name = "u_id")
    private int userId;
    @Column(name = "u_username", nullable = false, unique = true)
    private String userName;

    @Column(name = "u_email", nullable = false)
    private String emailId;

    @Column(name = "u_password", nullable = false)
    private String password;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

