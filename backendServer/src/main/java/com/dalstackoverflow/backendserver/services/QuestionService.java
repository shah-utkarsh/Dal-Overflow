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
import java.util.Optional;

/**
 * @author Sreejith Nair
 * This is the service class for posting and fetching questions
 */
@Service
public class QuestionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TagRepository tagRepository;
    /**
     * @param userQuestion
     * This method will be called on posting the questions from the Ask Question Page.
     */
    @Transactional
    public void postUserQuestion(Question userQuestion){
        LOGGER.info("Posting question for user : "+ userQuestion.getUserID());
        Question savedQuestion = questionRepository.save(userQuestion);
        LOGGER.info("Logged question id:"+savedQuestion.getQuestionID());
        for (String tagName : userQuestion.getTags()) {
            Tag tag = new Tag();
            tag.setTagName(tagName);
            tag.setQuestionID(savedQuestion.getQuestionID());
            tagRepository.save(tag);
            LOGGER.info("Saving tags");
        }
        LOGGER.info("Your question was successfully posted");
    }

    /**
     * This service method is responsible for calling the repository method to fetch the top questions.
     * @return List of top questions
     */
    @Transactional
    public List<Question> fetchTopQuestion(){
        System.out.println("fetchTopQuestion Called!!");
        //return questionRepository.findTopQuestions();
        List<Question> questionList= questionRepository.findTopQuestions();
        /* This is a temporary piece of code to fetch the data onthe home page. This will be modularized later*/
        for(Question question : questionList){
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

    /**
     * Searches for a Question object with the given questionID and returns it as an Optional.
     * @param questionID the ID of the question to search for.
     * @return an Optional containing the Question object if it exists, or an empty Optional if not.
     */
    public Optional<Question> searchQuestion(Integer questionID){

        return questionRepository.findById(questionID);
    }

    /**
     *
     * @param questionID
     * @return
     */
    public List<Tag> fetchTagsByQuestionID(Integer questionID){
        List<Tag> questiontags = tagRepository.findByQuestionID(questionID);
        return questiontags;
    }

    /**
     * This method will be called when delete button from the front end is hit.
     * @param questionID
     */
    public void deleteQuestion(Integer questionID) {
        Optional<Question> question = questionRepository.findById(questionID);
        if (question.isPresent()) {
            LOGGER.info("Question Found. Proceeding with delete");
            questionRepository.deleteById(questionID);
            LOGGER.info("Question with ID " + questionID + " has been deleted.");
        } else {
            LOGGER.info("Question with ID " + questionID + " does not exist.");
        }
    }
}
