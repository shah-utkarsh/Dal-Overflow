package com.dalstackoverflow.backendserver.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Utkarsh Shah
 * This is the model class for Answer table.
 * The model class will be used for posting and fetching answers from the UI.
 */
@Entity
@Table(name = "answer")
public class Answer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private int answerID;

    @Nonnull
    @Column(name = "q_id")
    private int questionID;

    @Nonnull
    @Column(name = "u_id")
    private int userID;

    @Nonnull
    @Column(name = "a_date")
    private LocalDateTime answerDate;

    @Column(name = "a_description")
    private String answerDescription;

    @Column(name = "a_votes")
    private int votes;

    @Column(name = "a_code")
    private String answerCode;

    @Column(name = "a_status", columnDefinition = "TINYINT(1)")
    private boolean answerStatus;

    @Nonnull
    @Column(name = "a_updates")
    private LocalDateTime answerUpdateStamp;

    //transient values
    @Transient
    List<Comment> allComments;

    public List<Comment> getAllComments() {
        return allComments;
    }

    public void setAllComments(List<Comment> allComments) {
        this.allComments = allComments;
    }

    // this will initialize the answerDate to the latest time when creating a answer in the datatbase and while fetching it wont chagne the date and time
    @PrePersist
    public void onCreate() {
        this.answerDate = LocalDateTime.now();
        this.answerUpdateStamp = LocalDateTime.now();
    }

    // constructor to call any method
    public Answer() {

    }

    // getters and setters
    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(LocalDateTime answerDate) {
        this.answerDate = answerDate;
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public void setAnswerDescription(String answerDescription) {
        this.answerDescription = answerDescription;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    public boolean isAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(boolean answerStatus) {
        this.answerStatus = answerStatus;
    }

    public LocalDateTime getAnswerUpdateStamp() {
        return answerUpdateStamp;
    }

    public void setAnswerUpdateStamp(LocalDateTime answerUpdateStamp) {
        this.answerUpdateStamp = answerUpdateStamp;
    }


    public void incrementVote() {
        this.votes++;
    }

    public void decrementVote() {
        this.votes--;
    }
}