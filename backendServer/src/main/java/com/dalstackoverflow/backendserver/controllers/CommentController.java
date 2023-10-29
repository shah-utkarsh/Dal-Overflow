package com.dalstackoverflow.backendserver.controllers;

import com.dalstackoverflow.backendserver.models.Comment;
import com.dalstackoverflow.backendserver.repositories.LoginRepository;
import com.dalstackoverflow.backendserver.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Utkarsh Shah
 * This is a controller class for comment api
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/{answerID}/comment")
public class CommentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);


    @Autowired
    CommentService commentService;

    @Autowired
    private LoginRepository userRepository;

    /**
     * @param comment
     * This method is used for posting the comment posted from the UI.
     */
    @PostMapping("/{userID}/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void postAnswerComment(@RequestBody Comment comment)
    {
        LOGGER.info("Calling comment Service");
        LOGGER.info("Request Object:"+comment.toString());
        commentService.postAnswerComment(comment);
    }
}
