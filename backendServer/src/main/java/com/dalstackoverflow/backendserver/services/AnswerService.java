package com.dalstackoverflow.backendserver.services;

import com.dalstackoverflow.backendserver.models.Answer;
import com.dalstackoverflow.backendserver.models.Comment;
import com.dalstackoverflow.backendserver.repositories.AnswerRepository;
import com.dalstackoverflow.backendserver.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Utkarsh Shah
 * This is the service class for posting and fetching answers
 */
@Service
public class AnswerService {

    @Autowired
    public AnswerRepository answerRepository;

    /**
     * Returns an Iterable of Answer objects that are associated with the given questionID.
     * @param questionID the ID of the question to retrieve answers for.
     * @return an List of Answer objects associated with the given questionID.
 */
    public List<Answer> getAllAnswerByQuestionID(Integer questionID) {
        return answerRepository.findByQuestionID(questionID);
    }

    @Autowired
    CommentRepository commentRepository;

    /**
     * This method will take input of list<Answer> and iterate over it and for each answer
     * it will fetch the corresponding comments and save it. Returns the list<Answer>
     * @param allAnswers
     * @return an list for answers with the saved comments for each one
     */
    public List<Answer> setCommentsForAnswer(List<Answer> allAnswers) {
        for( Answer answer: allAnswers){
            List<Comment> comments = commentRepository.fetchAnswerComments(answer.getAnswerID());
            answer.setAllComments(comments);
        }
        return allAnswers;
    }

    /**
     * @author Ritva Katrodiya
     * @return It will return iterable answer got from the DB
     */

    public Iterable<Answer> getAllUsers()
    {
        return answerRepository.findAll();
    }

    /**
     *
     * @param id it is used to send userId
     * @return answer object by particular userId
     */

    public Answer getUserById(int id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

}
