package com.dalstackoverflow.backendserver.controllers;

import com.dalstackoverflow.backendserver.models.Comment;
import com.dalstackoverflow.backendserver.repositories.LoginRepository;
import com.dalstackoverflow.backendserver.services.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private CommentController commentController;

    public CommentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void postAnswerComment_withCommentText_shouldCallCommentService() {
        Comment comment = new Comment();
        comment.setCommentText("Test comment");

        commentController.postAnswerComment(comment);

        verify(commentService, times(1)).postAnswerComment(comment);
    }


}