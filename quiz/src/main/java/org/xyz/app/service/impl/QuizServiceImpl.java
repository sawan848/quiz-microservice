package org.xyz.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.xyz.app.exception.QuizNotFoundException;
import org.xyz.app.feign.QuizInterface;
import org.xyz.app.model.Question;
import org.xyz.app.model.QuestionWrapper;
import org.xyz.app.model.Quiz;
import org.xyz.app.model.Response;
import org.xyz.app.repositry.QuizRepository;
import org.xyz.app.service.QuizService;
import org.xyz.app.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 8/21/2023
 * 2:14 PM
 */
@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;
    private final Map<String,List<Question>>questionCache=new ConcurrentHashMap<>();


    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuizInterface quizInterface) {
        this.quizRepository = quizRepository;
        this.quizInterface = quizInterface;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
      //call the generate url - RestTemplate
        try {
            List<Integer> questionIdList = quizInterface.getQuestionForQuiz(category, numQ).getBody();

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionsIds(questionIdList);
            quizRepository.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (QuizNotFoundException e){
            throw  new QuizNotFoundException("No table to create a quiz");
        }
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {
       try {
           Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionsIds = quiz.getQuestionsIds();
        return quizInterface.getQuestionsFromId(questionsIds);
       }catch (QuizNotFoundException e){
           throw  new QuizNotFoundException("there is no quiz with id "+id);

       }

    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {

        try {
            return quizInterface.getScore(responses);
        }catch (QuizNotFoundException e){
            throw  new QuizNotFoundException("there is no quiz with id "+id);


        }

    }
    @KafkaListener(topics = Constants.TOPIC_NAME, groupId = Constants.GROUP_ID)
    public void consumeQuestion(Question question) {
        System.out.println("question = " + question);
        String key = question.getCategory() + "-" + question.getDifficultyLevel();
        questionCache.computeIfAbsent(key, k -> new ArrayList<>()).add(question);
        System.out.println("questionCache = " + questionCache);

    }

    public List<Question>  createRandomQuiz(int numberOfQuestions) {
        List<Question> allQuestions = questionCache.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        // Implement logic to select random questions
        List<Question> questions = allQuestions.subList(0, Math.min(numberOfQuestions, allQuestions.size()));
//                new Quiz(questions.get(0).getId(),questions.get(0).getQuestionTitle(),)
        return questions;
//        return new Quiz(allQuestions.subList(0, Math.min(numberOfQuestions, allQuestions.size())));
    }

    public     List<Question>  createQuizByDifficultyAndCategory(String difficulty, String category) {
        String key = category + "-" + difficulty;
        List<Question> questions = questionCache.getOrDefault(key, new ArrayList<>());
        return questions;
//        return new Quiz(questions);
    }


}
//u should never trust own knowledge , u should have to always confirm with the db.
//bugs are part of life