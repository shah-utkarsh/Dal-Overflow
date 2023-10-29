package com.dalstackoverflow.backendserver.repositories;

import com.dalstackoverflow.backendserver.models.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sreejith Nair
 * This is the repository interface that will be used by the project for Questions.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    /**
     * This method will fire the query to return the list of latest questions from the database.
     * @return list of questions
     */
    @Query(value = "SELECT q_id,u_id,q_title,q_description,q_date,q_code FROM question ORDER BY q_date DESC LIMIT 8", nativeQuery = true)
    List<Question> findTopQuestions();

    /**
     * This method will fetch the patterns that will be matched from the keyword being sent by the user.
     * @param keyword
     * @return Question list that matached the keyword passed
     */
    List<Question> findByQuestionTitleContainingIgnoreCaseOrQuestionDescriptionContainingIgnoreCase(String titleKeyword, String descriptionKeyword);
}
