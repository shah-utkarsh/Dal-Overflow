package com.dalstackoverflow.backendserver.repositories;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dalstackoverflow.backendserver.models.Question;

import static org.mockito.Mockito.when;

/**
 * This is the question repository test class to check the integrity of the hibernate repository queries.
 * @author Sreejith Nair
 */
class QuestionRepositoryTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private Question question;

    /**
     * Initializing the mock objects
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        question = new Question(1, 1, "Question Title", "Question Description", "2022-04-07 13:47:15", "Question Code");
    }

    /**
     * This method is for testing the findTopQuestions Method.
     * It will assert if the results are satisfying the condition.
     */
    @Test
    public void testFindTopQuestions() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        questionList.add(new Question(2, 2, "Question Title 2", "Question Description 2", "2022-04-06 13:47:15", "Question Code 2"));

        when(questionRepository.findTopQuestions()).thenReturn(questionList);
        List<Question> foundQuestions = questionRepository.findTopQuestions();
        assertThat(foundQuestions).isEqualTo(questionList);
    }

    /**
     * This method is used to test the findByQuestionTitleContainingIgnoreCaseOrQuestionDescriptionContainingIgnoreCase method.
     */
    @Test
    public void testFindByQuestionTitleContainingIgnoreCaseOrQuestionDescriptionContainingIgnoreCase() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        questionList.add(new Question(2, 2, "Question Title 2", "Question Description 2", "2022-04-06 13:47:15", "Question Code 2"));

        when(questionRepository.findByQuestionTitleContainingIgnoreCaseOrQuestionDescriptionContainingIgnoreCase("title", "description")).thenReturn(questionList);
        List<Question> foundQuestions = questionRepository.findByQuestionTitleContainingIgnoreCaseOrQuestionDescriptionContainingIgnoreCase("title", "description");
        assertThat(foundQuestions).isEqualTo(questionList);
    }
}