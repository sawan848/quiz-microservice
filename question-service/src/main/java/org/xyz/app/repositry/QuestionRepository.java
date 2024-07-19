package org.xyz.app.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.xyz.app.model.Question;

import java.util.List;

/**
 * 6/26/2023
 * 10:59 AM
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query("select q from Question q where q.difficultyLevel LIKE %:difficultyLevel%")
    List<Question> findByDifficultyLevel(String difficultyLevel);

    @Query(value="SELECT q.id FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
    @Query(value="SELECT q.id FROM question q where q.difficulty_level=:difficultyLevel ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Integer> findRandomQuestionsByDifficultyLevel(String difficultyLevel, int numQ);
}
