package com.dalstackoverflow.backendserver.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Sreejith Nair
 * This is the model class for Question table.
 * The model class will be used for posting and fetching questions from the UI.
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@ToString
@Table(name = "question")
public class Question {
    /**
     * This the PK of the table and is auto incremented.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "q_id")
    private int questionID;

    @NonNull
    @Column(name = "u_id")
    private int userID;

    @NonNull
    @Column(name = "q_title")
    private String questionTitle;

    @NonNull
    @Column(name = "q_description")
    private String questionDescription;

    @NonNull
    @Column(name="q_code")
    private String questionCode;

    @NonNull
    @Column(name="q_date")
    private String questionDate;

    /*Temporary Code Start*/
    @Transient
    private String userName;

    @Transient
    private List<String> tags;

    @Transient
    private long answerCount;
    /*Temporary Code End*/

    // this is used to store all instance of all answers
    @Transient
    public List<Answer> allAnswers;

    /**
     * Creating this constructor to initialize question object for testing.
     * @param questionID
     * @param userID
     * @param questionTitle
     * @param questionDescription
     * @param questionCode
     * @param questionDate
     */
    public Question(int questionID, @NonNull int userID, @NonNull String questionTitle, @NonNull String questionDescription, @NonNull String questionCode, @NonNull String questionDate) {
        this.questionID = questionID;
        this.userID = userID;
        this.questionTitle = questionTitle;
        this.questionDescription = questionDescription;
        this.questionCode = questionCode;
        this.questionDate = questionDate;
        this.tags = new ArrayList<String>();
    }

    public List<Answer> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(List<Answer> allAnswers) {
        this.allAnswers = allAnswers;
    }

    /**
     * Adding default constructor to initialize the List.
     */
    public Question() {
        this.tags = new ArrayList<String>();
    }
}