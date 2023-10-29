package com.dalstackoverflow.backendserver.controllers;

import com.dalstackoverflow.backendserver.models.Answer;
import com.dalstackoverflow.backendserver.models.Question;
import com.dalstackoverflow.backendserver.models.Tag;
import com.dalstackoverflow.backendserver.services.AnswerService;
import com.dalstackoverflow.backendserver.services.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dalstackoverflow.backendserver.models.Answer;
import com.dalstackoverflow.backendserver.models.Question;
import com.dalstackoverflow.backendserver.services.AnswerService;
import com.dalstackoverflow.backendserver.services.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This is the Question Controller Test Class
 * @author Sreejith Nair
 */
@SpringBootTest
public class QuestionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    /**
     * This method will test the postUserQuestion method from the controller
     * @throws Exception
     */
    @Test
    public void testPostUserQuestion() throws Exception {
        Question question = new Question();
        question.setQuestionTitle("Test Question");
        question.setQuestionDescription("This is a test question.");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/question/postQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Question\",\"description\":\"This is a test question.\"}"))
                .andExpect(status().isCreated());
    }

    /**
     * This method will test the fetchTopUserQuestions method from the controller
     * @throws Exception
     */
    public void testFetchTopUserQuestions() throws Exception {
        Question question1 = new Question();
        question1.setQuestionTitle("Test Question 1");
        question1.setQuestionDescription("This is a test question 1.");
        Question question2 = new Question();
        question2.setQuestionTitle("Test Question 2");
        question2.setQuestionDescription("This is a test question 2.");
        List<Question> questions = Arrays.asList(question1, question2);
        when(questionService.fetchTopQuestion()).thenReturn(questions);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/question/fetchTopQuestions"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Test Question 1"))
                .andExpect(jsonPath("$[0].description").value("This is a test question 1."))
                .andExpect(jsonPath("$[1].title").value("Test Question 2"))
                .andExpect(jsonPath("$[1].description").value("This is a test question 2."));
    }
}


