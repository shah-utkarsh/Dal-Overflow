package com.dalstackoverflow.backendserver.repositories;

import com.dalstackoverflow.backendserver.models.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Integer> {

    /**
     * This method used to get all answers for a particular questionID
     * @param questionID is used to check which answers has this questionID
     * @return List<Answer> to the AnswerService Class
     */
    @Query(value = "select * from answer where q_id =:questionID " ,nativeQuery = true)
    List<Answer> findByQuestionID(@Param("questionID")Integer questionID);

    /**
     * This method is used to fetch the total number of answers for a particular question
     * @param questionID
     * @return total number of answers for a question
     */
    long countByQuestionID(int questionID);

}
