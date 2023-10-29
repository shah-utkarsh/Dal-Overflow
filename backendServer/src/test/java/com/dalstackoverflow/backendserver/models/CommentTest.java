package com.dalstackoverflow.backendserver.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommentTest {
    @Test
    public void testOnCreate() {
        Comment comment = new Comment();
        comment.onCreate();
        assertNotNull(comment.getCommentDate());
    }

    @Test
    public void testGetSetAnswerID() {
        Comment comment = new Comment();
        comment.setAnswerID(123);
        assertEquals(123, comment.getAnswerID());
    }

    @Test
    public void testGetSetUserID() {
        Comment comment = new Comment();
        comment.setUserID(456);
        assertEquals(456, comment.getUserID());
    }


    @Test
    public void testGetSetCommentText() {
        Comment comment = new Comment();
        comment.setCommentText("This is a test comment");
        assertEquals("This is a test comment", comment.getCommentText());
    }

    @Test
    public void testAnswerID() {
        Comment comment = new Comment();
        int answerID = 1;
        comment.setAnswerID(answerID);
        assertEquals(answerID, comment.getAnswerID());
    }

    @Test
    public void testCommentID() {
        Comment comment = new Comment();
        int commentID = 1;
        comment.setCommentID(commentID);
        assertEquals(commentID, comment.getCommentID());
    }

    @Test
    public void testUserID() {
        Comment comment = new Comment();
        int userID = 1;
        comment.setUserID(userID);
        assertEquals(userID, comment.getUserID());
    }

    @Test
    public void testCommentText() {
        Comment comment = new Comment();
        String commentText = "This is a test comment";
        comment.setCommentText(commentText);
        assertEquals(commentText, comment.getCommentText());
    }

    @Test
    public void testCommentDate() {
        Comment comment = new Comment();
        LocalDateTime commentDate = LocalDateTime.now();
        comment.setCommentDate(commentDate);
        assertEquals(commentDate, comment.getCommentDate());
    }


}
