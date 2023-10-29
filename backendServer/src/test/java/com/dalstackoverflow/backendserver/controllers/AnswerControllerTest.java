package com.dalstackoverflow.backendserver.controllers;

import com.dalstackoverflow.backendserver.models.Answer;
import com.dalstackoverflow.backendserver.repositories.AnswerRepository;
import com.dalstackoverflow.backendserver.services.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnswerControllerTest {

    @Mock
    private AnswerService answerService;

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerController answerController;

    private Answer answer;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        answer = new Answer();
        answer.setAnswerID(1);
        answer.setQuestionID(1);
        answer.setUserID(1);
        answer.setAnswerDescription("This is an answer.");
        answer.setVotes(0);
        answer.setAnswerStatus(false);
    }

    @Test
    void testGetAllAnswers() {
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);

        when(answerService.getAllAnswerByQuestionID(1)).thenReturn(answers);

        List<Answer> result = answerController.getAllAnswers(1);

        assertEquals(result, answers);
    }

    @Test
    void testSaveOrUpdateAnswer() {
        when(answerRepository.save(answer)).thenReturn(answer);

        ResponseEntity<?> responseEntity = answerController.saveOrUpdateAnswer(1, 1, answer);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(responseEntity.getBody(), answer);
    }

    @Test
    void testUpdateAnswerVote() {
        Optional<Answer> optionalAnswer = Optional.of(answer);

        when(answerRepository.findById(1)).thenReturn(optionalAnswer);
        when(answerRepository.save(answer)).thenReturn(answer);

        ResponseEntity<?> responseEntity = answerController.updateAnswerVote(1, 1);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), answer);
    }

    @Test
    void testSelectedAnswer() {
        Optional<Answer> optionalAnswer = Optional.of(answer);

        when(answerRepository.findById(1)).thenReturn(optionalAnswer);
        when(answerRepository.save(answer)).thenReturn(answer);


        Optional<Answer> optionalAnswer1 = answerRepository.findById(1);
        Answer answer1 = answerRepository.save(answer);
        assertEquals(optionalAnswer1.get(),answer);
        assertEquals(answer1,answer);

    }
    @Test
    void updateAnswerVoteTest_withInvalidDirection_shouldThrowException() {

        int answerId = 1;
        int direction = 0;
        Answer answer = new Answer();
        answer.setVotes(0);
        when(answerRepository.findById(answerId)).thenReturn(Optional.of(answer));

        assertThrows(IllegalArgumentException.class, () -> {
            answerController.updateAnswerVote(answerId, direction);
        });
    }

    @Test
    void testUpdateAnswerVote_withNegativeDirection() {
        Optional<Answer> optionalAnswer = Optional.of(answer);

        when(answerRepository.findById(1)).thenReturn(optionalAnswer);
        when(answerRepository.save(answer)).thenReturn(answer);

        ResponseEntity<?> responseEntity = answerController.updateAnswerVote(1, -1);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), answer);
    }

}
