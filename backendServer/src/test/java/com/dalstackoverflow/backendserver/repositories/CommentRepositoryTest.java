package com.dalstackoverflow.backendserver.repositories;

import com.dalstackoverflow.backendserver.models.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CommentRepositoryTest {

    @Test
    void testFetchAnswerComments() {

        CommentRepository commentRepository = mock(CommentRepository.class);
        int answerId = 1;
        Comment comment1 = new Comment();
        comment1.setAnswerID(answerId);
        comment1.setCommentText("Comment 1");
        Comment comment2 = new Comment();
        comment2.setAnswerID(answerId);
        comment2.setCommentText("Comment 2");
        Comment comment3 = new Comment();
        comment3.setAnswerID(answerId + 1);
        comment3.setCommentText("Comment 3");
        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3));
        when(commentRepository.fetchAnswerComments(answerId)).thenReturn(Arrays.asList(comment1, comment2));


        List<Comment> comments = commentRepository.fetchAnswerComments(answerId);


        assertEquals(2, comments.size());
        assertTrue(comments.contains(comment1));
        assertTrue(comments.contains(comment2));
    }

}