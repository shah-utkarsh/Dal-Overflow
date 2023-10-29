package com.dalstackoverflow.backendserver.services;

import com.dalstackoverflow.backendserver.models.Question;
import com.dalstackoverflow.backendserver.models.Registration;
import com.dalstackoverflow.backendserver.models.Tag;

import com.dalstackoverflow.backendserver.repositories.AnswerRepository;
import com.dalstackoverflow.backendserver.repositories.LoginRepository;
import com.dalstackoverflow.backendserver.repositories.QuestionRepository;
import com.dalstackoverflow.backendserver.repositories.TagRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This is the question service test class that will cover all the questions services related test cases
 * Some methods will be redundant as of now but will be resolved after based on timely refactoring.
 * @author Sreejith Nair
 */
class QuestionServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceTest.class);
    private Question question;
    private Registration user;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;

    private AnswerRepository answerRepository;

    private TagRepository tagRepository;

    private LoginRepository loginRepository;

    @BeforeEach
    void setUp() {
        LOGGER.info("Setting up dummy mock object and initializing mock repositories");
        question = new Question(1,1,"Title 1", "Description 1","Code 1","Date 1");
        /*
          Initializing User dummy objects.
         */
        user = new Registration();
        user.setUserId(1);
        user.setUserName("username");

        /*
            Initializing Mock classes.
         */
        questionRepository = mock(QuestionRepository.class);
        answerRepository = mock(AnswerRepository.class);
        tagRepository = mock(TagRepository.class);
        loginRepository = mock(LoginRepository.class);
        //MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * This methods tests the basic posting functionality of the question service.
     */
    @Test
    void testPostUserQuestion() {
        QuestionService questionService = new QuestionService();
        /*
            questionRepository is already mocked in the setUp function before each test.
            Reusing it here.
         */
        questionService.questionRepository = questionRepository;
        when(questionRepository.save(question)).thenReturn(question);
        /*
        this is not required as of now since we need to test the save method as well.
        doNothing().when(mockRepository).save(any(Question.class));
         */
        LOGGER.info("Executing Test testPostUserQuestion");
        questionService.postUserQuestion(question);
        verify(questionService.questionRepository, times(1)).save(question);
    }

    /**
     * This method tests the question search functionality that is
     * used by the question answer rendering thread.
     */
    @Test
    void testSearchQuestion() {
        QuestionRepository mockRepository = mock(QuestionRepository.class);
        when(mockRepository.findById(1)).thenReturn(Optional.of(question));

        QuestionService questionService = new QuestionService();
        questionService.questionRepository = mockRepository;

        /*Fetching the search question result for the mock object*/
        Optional<Question> result = questionService.searchQuestion(1);

        /*Verifying if findByID() was called only once during this process*/
        verify(mockRepository, times(1)).findById(1);

        /*Checking if result contains the same object as the mock object*/
        assertEquals(question, result.get());
    }

    /**
     * This method is used to test the fetchTopQuestion from question service class.
     */
    @Test
    public void testFetchTopQuestion() {

        /*
           The mock classes being used are already initialized in the setup method
           Below code ensures that the mocked classes are accessible to questionService.
         */

        QuestionService questionService = new QuestionService();
        questionService.questionRepository = questionRepository;
        questionService.answerRepository = answerRepository;
        questionService.tagRepository = tagRepository;
        questionService.loginRepository = loginRepository;

        when(questionRepository.findTopQuestions()).thenReturn(Arrays.asList(
                new Question(1,1,"Title 1", "Description 1","Code 1","Date 1"),
                new Question(2,2,"Title 2", "Description 2","Code 2","Date 2"),
                new Question(3,3,"Title 3", "Description 3","Code 3","Date 3")
        ));

        when(loginRepository.findById(anyInt())).thenReturn(user);
        when(tagRepository.findByQuestionID(anyInt())).thenReturn(Arrays.asList(new Tag(1,1,"Java"), new Tag(2,1,"Spring")));
        when(answerRepository.countByQuestionID(anyInt())).thenReturn(2L);


        List<Question> topQuestions = questionService.fetchTopQuestion();

        /*
           Asserting the outputs here
         */
        assertEquals(3, topQuestions.size());

        assertEquals("Title 1", topQuestions.get(0).getQuestionTitle());
        assertEquals("Description 1", topQuestions.get(0).getQuestionDescription());
        assertEquals("username", topQuestions.get(0).getUserName());
        assertEquals(2, topQuestions.get(0).getTags().size());
        assertEquals("Java", topQuestions.get(0).getTags().get(0));
        assertEquals(2L, topQuestions.get(0).getAnswerCount());
    }
}