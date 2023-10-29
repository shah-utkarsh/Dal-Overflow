package com.dalstackoverflow.backendserver.services;

import com.dalstackoverflow.backendserver.models.Question;
import com.dalstackoverflow.backendserver.models.Registration;
import com.dalstackoverflow.backendserver.models.Tag;
import com.dalstackoverflow.backendserver.repositories.AnswerRepository;
import com.dalstackoverflow.backendserver.repositories.LoginRepository;
import com.dalstackoverflow.backendserver.repositories.QuestionRepository;
import com.dalstackoverflow.backendserver.repositories.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sreejith Nair
 * This is the service class for prroviding search mechanism.
 */
@Service
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TagRepository tagRepository;

    /**
     * This service method is responsible for calling the repository method to fetch the question being serached by the user from the serach box.
     * @return List of top questions
     */
    @Transactional
    public List<Question> searchPostedQuestions(String keyword){
        System.out.println("searchPostedQuestions Called!!");

        List<Question> questionList= questionRepository.findByQuestionTitleContainingIgnoreCaseOrQuestionDescriptionContainingIgnoreCase(keyword,keyword);

        /* This is a temporary piece of code that is written to relate with the exisitng question return module
        * this will be modified later*/
        for(Question question : questionList){
            LOGGER.info("Question :"+question.getQuestionTitle());
            Registration user = loginRepository.findById(question.getUserID());
            question.setUserName(user.getUserName());
            List<Tag> questiontags = tagRepository.findByQuestionID(question.getQuestionID());
            if(!questiontags.isEmpty())
            for(Tag questionTag : questiontags){
                LOGGER.info("Question id :"+question.getQuestionID());
                LOGGER.info("Found tag :"+questionTag.getTagName());
                question.getTags().add(questionTag.getTagName());
            }
            question.setAnswerCount(answerRepository.countByQuestionID(question.getQuestionID()));
        }
        return questionList;
    }
}
