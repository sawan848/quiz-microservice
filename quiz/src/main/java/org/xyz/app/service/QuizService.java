package org.xyz.app.service;

import org.springframework.http.ResponseEntity;
import org.xyz.app.model.Question;
import org.xyz.app.model.QuestionWrapper;
import org.xyz.app.model.Response;

import java.util.List;

public interface QuizService {
    ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) ;
    ResponseEntity<Integer> calculateResult(int id, List<Response> responses) ;
    ResponseEntity<String> createQuiz(String category, int numQ, String title) ;
    List<Question>  createRandomQuiz(int numberOfQuestions);
    List<Question>  createQuizByDifficultyAndCategory(String difficulty, String category) ;
}
