package com.dalstackoverflow.backendserver.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer();
    }

    @Test
    void testAnswerConstructor() {
        assertNotNull(answer);
    }

    @Test
    void testAnswerID() {
        answer.setAnswerID(1);
        assertEquals(1, answer.getAnswerID());
    }

    @Test
    void testQuestionID() {
        answer.setQuestionID(1);
        assertEquals(1, answer.getQuestionID());
    }

    @Test
    void testUserID() {
        answer.setUserID(1);
        assertEquals(1, answer.getUserID());
    }

    @Test
    void testAnswerDate() {
        LocalDateTime date = LocalDateTime.now();
        answer.setAnswerDate(date);
        assertEquals(date, answer.getAnswerDate());
    }

    @Test
    void testAnswerDescription() {
        answer.setAnswerDescription("This is an answer description");
        assertEquals("This is an answer description", answer.getAnswerDescription());
    }

    @Test
    void testVotes() {
        answer.setVotes(1);
        assertEquals(1, answer.getVotes());
    }

    @Test
    void testAnswerCode() {
        answer.setAnswerCode("public static void main(String[] args) {}");
        assertEquals("public static void main(String[] args) {}", answer.getAnswerCode());
    }

    @Test
    void testAnswerStatus() {
        answer.setAnswerStatus(true);
        assertTrue(answer.isAnswerStatus());
    }

    @Test
    void testAnswerUpdateStamp() {
        LocalDateTime updateStamp = LocalDateTime.now();
        answer.setAnswerUpdateStamp(updateStamp);
        assertEquals(updateStamp, answer.getAnswerUpdateStamp());
    }

    @Test
    void testIncrementVote() {
        answer.setVotes(0);
        answer.incrementVote();
        assertEquals(1, answer.getVotes());
    }

    @Test
    void testDecrementVote() {
        answer.setVotes(1);
        answer.decrementVote();
        assertEquals(0, answer.getVotes());
    }

    @Test
    void testGetAllCommentsWhenAllCommentsIsNull() {
        assertNull(answer.getAllComments());
    }

    @Test
    void testOnCreate() {
        LocalDateTime now = LocalDateTime.now();
        answer.onCreate();
        LocalDateTime currentTime = now.withNano(now.getNano() / 1000000 * 1000000);
        LocalDateTime answerDate =  (answer.getAnswerDate().withNano(now.getNano() / 1000000 * 1000000));
        LocalDateTime answerUpdateDate =  (answer.getAnswerUpdateStamp().withNano(now.getNano() / 1000000 * 1000000));
        assertEquals(currentTime,answerDate );
        assertEquals(currentTime,answerUpdateDate);
    }

    @Test
    void testSetAllCommentsWhenAllCommentsIsNotNull() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        answer.setAllComments(comments);
        assertEquals(comments, answer.getAllComments());
    }


}
