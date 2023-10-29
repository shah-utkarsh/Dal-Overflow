package com.dalstackoverflow.backendserver.repositories;

import com.dalstackoverflow.backendserver.models.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AnswerRepositoryTest {

    //Mock of answer Repository
    AnswerRepository answerRepository = mock(AnswerRepository.class);
    @Test
    public void testFindByQuestionID() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer());
        answers.add(new Answer());
        when(answerRepository.findByQuestionID(anyInt())).thenReturn(answers);

        List<Answer> result = answerRepository.findByQuestionID(1);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testCountByQuestionID() {
        when(answerRepository.countByQuestionID(anyInt())).thenReturn(2L);

        long count = answerRepository.countByQuestionID(1);

        assertEquals(2, count);
    }

    @Test
    public void testUpdate() {
        Answer answer = new Answer();
        answer.setAnswerID(1);
        answer.setAnswerCode("This is an updated answer");

        Optional<Answer> optionalAnswer = Optional.of(answer);
        when(answerRepository.findById(anyInt())).thenReturn(optionalAnswer);
        when(answerRepository.save(answer)).thenReturn(answer);

        Answer updatedAnswer = answerRepository.save(answer);

        assertNotNull(updatedAnswer);
        assertEquals("This is an updated answer", updatedAnswer.getAnswerCode());
    }

    @Test
    public void testDelete() {
        Answer answer = new Answer();
        answer.setAnswerID(1);
        answerRepository.delete(answer);

        assertFalse(answerRepository.findById(1).isPresent());
    }
    @Test
     void testSave() {
        Answer answer = new Answer();
        answer.setQuestionID(1);
        answer.setUserID(2);
        answer.setAnswerCode("This is a new answer");

        answerRepository.save(answer);
        verify(answerRepository, times(1)).save(answer);
    }



}
