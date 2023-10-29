package com.dalstackoverflow.backendserver.services;

import com.dalstackoverflow.backendserver.models.Comment;
import com.dalstackoverflow.backendserver.repositories.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Utkarsh Shah
 * This is the service class for posting and fetching comments
 */
@Service
public class CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    CommentRepository commentRepository;

    /**
     * This methods takes comment as input and save it in the database for a particular answer id
     * @param answerComment is a comment of a particular answer of Comment type
     */
    @Transactional
    public void postAnswerComment(Comment answerComment){
        LOGGER.info("Posting comment for answer : "+ answerComment.getAnswerID());
        commentRepository.save(answerComment);
        LOGGER.info("Your comment was successfully posted");
    }

}
