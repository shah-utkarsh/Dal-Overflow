package com.dalstackoverflow.backendserver.services;

import com.dalstackoverflow.backendserver.models.Answer;
import com.dalstackoverflow.backendserver.models.Comment;
import com.dalstackoverflow.backendserver.repositories.AnswerRepository;
import com.dalstackoverflow.backendserver.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AnswerServiceTest {

    @Test
    public void testGetAllAnswerByQuestionID() {
        AnswerService answerService =new AnswerService();
        answerService.answerRepository = mock(AnswerRepository.class);
        int questionID = 1;
        Answer answer1 = new Answer();
        answer1.setAnswerID(1);
        answer1.setAnswerCode("Answer 1");
        Answer answer2 = new Answer();
        answer2.setAnswerID(2);
        answer2.setAnswerCode("Answer 2");
        List<Answer> answers = Arrays.asList(answer1, answer2);
        when(answerService.answerRepository.findByQuestionID(questionID)).thenReturn(answers);

        List<Answer> result = answerService.getAllAnswerByQuestionID(questionID);

        assertEquals(answers, result);
        verify(answerService.answerRepository).findByQuestionID(questionID);
    }

    @Test
    public void testSetCommentsForAnswer() {

        AnswerService answerService =new AnswerService();
        answerService.answerRepository = mock(AnswerRepository.class);
        answerService.commentRepository= mock(CommentRepository.class);
        Answer answer1 = new Answer();
        answer1.setAnswerID(1);
        Answer answer2 = new Answer();
        answer2.setAnswerID(2);
        List<Answer> answers = Arrays.asList(answer1, answer2);
        Comment comment1 = new Comment();
        comment1.setAnswerID(1);
        comment1.setCommentText("Comment 1");
        Comment comment2 = new Comment();
        comment2.setAnswerID(1);
        comment2.setCommentText("Comment 2");
        Comment comment3 = new Comment();
        comment3.setAnswerID(2);
        comment3.setCommentText("Comment 3");
        when(answerService.commentRepository.fetchAnswerComments(1)).thenReturn(Arrays.asList(comment1, comment2));
        when(answerService.commentRepository.fetchAnswerComments(2)).thenReturn(Arrays.asList(comment3));

        List<Answer> result = answerService.setCommentsForAnswer(answers);

        assertEquals(2, result.get(0).getAllComments().size());
        assertTrue(result.get(0).getAllComments().contains(comment1));
        assertTrue(result.get(0).getAllComments().contains(comment2));
        assertEquals(1, result.get(1).getAllComments().size());
        assertTrue(result.get(1).getAllComments().contains(comment3));
        verify(answerService.commentRepository, times(2)).fetchAnswerComments(anyInt());
    }

    @Test
    public void testGetAllUsers() {

        AnswerService answerService =new AnswerService();
        answerService.answerRepository = mock(AnswerRepository.class);
        List<Answer> answers = Arrays.asList(new Answer(), new Answer());
        when(answerService.answerRepository.findAll()).thenReturn(answers);

        Iterable<Answer> result = answerService.getAllUsers();

        assertEquals(answers, result);
        verify(answerService.answerRepository).findAll();
    }

    @Test
    public void testGetUserById() {
        AnswerService answerService =new AnswerService();
        answerService.answerRepository = mock(AnswerRepository.class);
        int id = 1;
        Answer answer = new Answer();
        answer.setAnswerID(id);
        when(answerService.answerRepository.findById(id)).thenReturn(Optional.of(answer));

        Answer result = answerService.getUserById(id);

        assertEquals(answer, result);
        verify(answerService.answerRepository).findById(id);
    }

    @Test
    public void testGetUserByIdNotFound() {
        AnswerService answerService =new AnswerService();
        answerService.answerRepository = mock(AnswerRepository.class);
        int id = 1;
        when(answerService.answerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            answerService.getUserById(id);
        });

    }

}
