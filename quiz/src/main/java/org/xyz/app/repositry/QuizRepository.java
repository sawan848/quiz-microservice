package org.xyz.app.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xyz.app.model.Quiz;

/**
 * 8/21/2023
 * 2:15 PM
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
