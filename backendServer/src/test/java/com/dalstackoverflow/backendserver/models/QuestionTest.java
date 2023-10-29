package com.dalstackoverflow.backendserver.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * This is the test class for Question Model class
 * @author Sreejith Nair
 */
public class QuestionTest {

    /**
     * This method will test for constructors and the getter methods fo the model class.
     */
    @Test
    public void testConstructorAndGetters() {
        int questionID = 1;
        int userID = 2;
        String questionTitle = "Test Quest";
        String questionDescription = "This is a test question";
        String questionCode = "public static void main(String[] args) { }";
        String questionDate = "2022-04-07";

        Question question = new Question(questionID, userID, questionTitle, questionDescription, questionCode, questionDate);

        assertEquals(questionID, question.getQuestionID());
        assertEquals(userID, question.getUserID());
        assertEquals(questionTitle, question.getQuestionTitle());
        assertEquals(questionDescription, question.getQuestionDescription());
        assertEquals(questionCode, question.getQuestionCode());
        assertEquals(questionDate, question.getQuestionDate());
        //assertNotNull(question.getTags());
        //assertNotNull(question.getAllAnswers());
    }

    /**
     * This method will check the setter methods for the question model class
     */
    @Test
    public void testSetters() {
        int questionID = 1;
        int userID = 2;
        String questionTitle = "Test Question";
        String questionDescription = "This is a test question";
        String questionCode = "public static void main(String[] args) { }";
        String questionDate = "2022-04-07";

        Question question = new Question();
        question.setQuestionID(questionID);
        question.setUserID(userID);
        question.setQuestionTitle(questionTitle);
        question.setQuestionDescription(questionDescription);
        question.setQuestionCode(questionCode);
        question.setQuestionDate(questionDate);

        assertEquals(questionID, question.getQuestionID());
        assertEquals(userID, question.getUserID());
        assertEquals(questionTitle, question.getQuestionTitle());
        assertEquals(questionDescription, question.getQuestionDescription());
        assertEquals(questionCode, question.getQuestionCode());
        assertEquals(questionDate, question.getQuestionDate());
        //assertNotNull(question.getTags());
        //assertNotNull(question.getAllAnswers());
    }

    /**
     * This method is used to test the Tags and Answer collection in the model class.
     */
    @Test
    public void testTagsAndAnswers() {
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring");

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer());

        Question question = new Question(1, 1, "Title", "Description", "Code", "2022-03-14");
        question.setTags(tags);
        question.setAllAnswers(answers);

        assertEquals(tags, question.getTags());
        assertEquals(answers, question.getAllAnswers());
    }
}
