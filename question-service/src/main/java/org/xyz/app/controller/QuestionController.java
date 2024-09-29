package org.xyz.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xyz.app.dto.QuestionResponse;
import org.xyz.app.dto.Response;
import org.xyz.app.model.Question;
import org.xyz.app.service.QuestionService;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

/**
 * 6/26/2023
 * 11:31 AM
 */
@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {
    private final QuestionService questionService;
    @Autowired
    Environment environment;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/save")
    public ResponseEntity<Question>saveQuestion(@RequestBody Question question){
        System.out.println("question = " + question);
          return ResponseEntity.status(HttpStatus.CREATED).body(questionService.saveQuestion(question));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Question>updateQuestion(@PathVariable int id,@RequestBody Question question){
          return ResponseEntity.status(HttpStatus.OK).body( questionService.updateQuestionById(id, question));

    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String>deleteQuestion(@PathVariable int id){
          return ResponseEntity.status(HttpStatus.ACCEPTED).body( questionService.deleteQuestionById(id));

    }
    @GetMapping("/all")
    public ResponseEntity<List<Question>>allQuestion(){
        DateTimeFormatter ZDT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
        String str=ZDT_FORMATTER.format(ZonedDateTime.now());




        System.out.println("calling service from port: "+environment.getProperty("local.server.port") +"at "+str);
          return ResponseEntity.status(HttpStatus.OK).body(questionService.allQuestion());
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>>getQuestionByCategory(@PathVariable String category){
          return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionByCategory(category));
    }
    @GetMapping("/level/{level}")
    public ResponseEntity<List<Question>>getQuestionByDifficultyLevel(@PathVariable String level){
          return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionByDifficultyLevel(level));
    }

    //generate question
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,@RequestParam Integer noOfQuestion){
          return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionForQuiz(category,noOfQuestion));
    }
    //getQuestion(id)
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionResponse>>getQuestions(@RequestBody List<Integer>questionIds){
          return ResponseEntity.status(HttpStatus.CREATED).body(questionService.getQuestionFromId(questionIds));
    }
    //getScore()

    @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response>responses){
          return ResponseEntity.status(HttpStatus.CREATED).body(questionService.getScore(responses));
    }
}
