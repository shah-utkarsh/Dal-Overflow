package com.dalstackoverflow.backendserver.services;

import com.dalstackoverflow.backendserver.models.Comment;
import com.dalstackoverflow.backendserver.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommentServiceTest {

    @Test
    public void testPostAnswerCommentSuccess() {
        CommentService commentService = new CommentService();
        commentService.commentRepository = mock(CommentRepository.class);

        Comment comment = new Comment();
        comment.setAnswerID(1);
        comment.setCommentText("This is a test comment");



        commentService.postAnswerComment(comment);

        when(commentService.commentRepository.fetchAnswerComments(1)).thenReturn(Arrays.asList(comment));

        List<Comment> comments = commentService.commentRepository.fetchAnswerComments(1);
        assertEquals(1, comments.size());
        Comment savedComment = comments.get(0);
        assertEquals(1, savedComment.getAnswerID());
        assertEquals("This is a test comment", savedComment.getCommentText());
    }

    @Test
    public void testPostAnswerCommentEmptyText() {
        CommentService commentService = mock(CommentService.class);

        Comment comment = new Comment();
        comment.setAnswerID(1);
        comment.setCommentText("");


        commentService.postAnswerComment(comment);
    }


    @Test
    public void testPostAnswerCommentNullAnswerID() {
        CommentService commentService = new CommentService();
        commentService.commentRepository = mock(CommentRepository.class);

        Comment comment = new Comment();
        comment.setCommentText("This is a test comment");

            commentService.postAnswerComment(comment);

    }


    @Test
    public void testPostAnswerCommentNonExistingAnswerID() {
        CommentService commentService = mock(CommentService.class);

        Comment comment = new Comment();
        comment.setAnswerID(999);
        comment.setCommentText("This is a test comment");

        commentService.postAnswerComment(comment);

    }

    @Test
    public void testPostAnswerCommentMultiple() {
        CommentService commentService = new CommentService();
        commentService.commentRepository = mock(CommentRepository.class);

        Comment comment1 = new Comment();
        comment1.setAnswerID(1);
        comment1.setCommentText("This is the first comment");

        Comment comment2 = new Comment();
        comment2.setAnswerID(1);
        comment2.setCommentText("This is the second comment");


        commentService.postAnswerComment(comment1);
        commentService.postAnswerComment(comment2);

        when(commentService.commentRepository.fetchAnswerComments(1)).thenReturn(Arrays.asList(comment1,comment2));

        List<Comment> comments = commentService.commentRepository.fetchAnswerComments(1);
        assertEquals(2, comments.size());
        assertEquals("This is the first comment", comments.get(0).getCommentText());
        assertEquals("This is the second comment", comments.get(1).getCommentText());
    }

    @Test
    public void testPostAnswerCommentNullText() {
        CommentService commentService = mock(CommentService.class);


        Comment comment = new Comment();
        comment.setAnswerID(1);
        comment.setCommentText(null);


        commentService.postAnswerComment(comment);
    }



}
