package com.dalstackoverflow.backendserver.controllers;

import com.dalstackoverflow.backendserver.models.Answer;
import com.dalstackoverflow.backendserver.models.Question;
import com.dalstackoverflow.backendserver.models.Tag;
import com.dalstackoverflow.backendserver.services.AnswerService;
import com.dalstackoverflow.backendserver.services.QuestionService;
import com.dalstackoverflow.backendserver.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Sreejith Nair
 * This is the primary controller class of the Search Questions api.
 */

@CrossOrigin
@RestController
@RequestMapping("api/search")
public class SearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    /**
     * This method will be called from the homepage Search box.
     * fetch the list of question that match the user string from the search box.
     * @return list of questions fetched by the api call.
     */
    @GetMapping("/searchQuestions")
    public List<Question> searchPostedQuestion(@RequestParam String keyword){
        LOGGER.info("Searching posted user questions");
        List<Question> questionList = searchService.searchPostedQuestions(keyword);
        return questionList;
    }
}
