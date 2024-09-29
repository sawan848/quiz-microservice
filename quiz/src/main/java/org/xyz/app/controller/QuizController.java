package org.xyz.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xyz.app.model.Question;
import org.xyz.app.model.QuestionWrapper;
import org.xyz.app.model.QuizDto;
import org.xyz.app.model.Response;
import org.xyz.app.service.QuizService;

import java.util.List;

/**
 * 8/21/2023
 * 2:04 PM
 */
@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService){
        this.quizService=quizService;
    }


    @PostMapping("/save/category")
    public ResponseEntity<String>createQuiz(@RequestBody QuizDto quizDto){
        return  quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumOfQuestion(),quizDto.getTitle());
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable int id, @RequestBody List<Response>responses){
        return  quizService.calculateResult(id,responses);

    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestion(@PathVariable int id){
        return quizService.getQuizQuestion(id);
    }
    @GetMapping("/difficultyLevel/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestionByDifficultyLevel(@PathVariable int id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("/random")
    public List<Question>  createRandomQuiz(@RequestParam int numberOfQuestions) {
        return quizService. createRandomQuiz(numberOfQuestions);
    }

    @PostMapping("/bydifficultyandcategory")
    public List<Question> createQuizByDifficultyAndCategory(@RequestParam String difficulty, @RequestParam String category) {
        return quizService.createQuizByDifficultyAndCategory(difficulty, category);
    }


}
//u tell me which service u want to connect i will connected for u