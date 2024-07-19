package org.xyz.app.service;

import org.xyz.app.dto.QuestionResponse;
import org.xyz.app.dto.Response;
import org.xyz.app.model.Question;

import java.util.List;

/**
 * 12/12/2023
 * 6:20 PM
 */

public interface QuestionService {

     Question  saveQuestion(Question question);

     Question  updateQuestionById(int id, Question question);

     List<Question>  allQuestion();

     List<Question>  getQuestionByCategory(String category);

     String  deleteQuestionById(int id);

     List<Question>  getQuestionByDifficultyLevel(String level);

     List<Integer>  getQuestionForQuiz(String category, Integer noOfQuestion);

     List<QuestionResponse>  getQuestionFromId(List<Integer>  questionIds);

     Integer  getScore(List<Response>  responses);
}
