package com.dalstackoverflow.backendserver.repositories;

import com.dalstackoverflow.backendserver.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is the repository class for fetching tag related data from the database.
 * @author Sreejith Nair
 */
@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
    /**
     * This method will fire the query to return the list of tags associated to a question from the database.
     * @return list of tags
     */
    List<Tag> findByQuestionID(int questionID);
}