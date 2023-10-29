package com.dalstackoverflow.backendserver.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author Utkarsh Shah
 * This is the model class for Comment table.
 * The model class will be used for posting and fetching comments from the UI.
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int commentID;


    @Column(name = "a_id", nullable = false)
    private int answerID;


    @Column(name = "u_id",nullable = false)
    private int userID;

    @Column(name= "c_date",nullable = false)
    private LocalDateTime commentDate;

    @Column(name = "c_description")
    private String commentText;


    @PrePersist
    public void onCreate() {
        this.commentDate = LocalDateTime.now();
    }
    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Comment(){

    }
}
