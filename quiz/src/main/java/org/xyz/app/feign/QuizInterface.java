package org.xyz.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.xyz.app.model.QuestionWrapper;
import org.xyz.app.model.Response;

import java.util.List;

/**
 * 8/22/2023
 * 7:36 AM
 */
@FeignClient("QUESTION-SERVICE/api/v1/questions")
public interface QuizInterface {
    //generate question
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer noOfQuestion);

    //getQuestion(id)
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionsFromId(@RequestBody List<Integer>questionIds);

    //getScore()
    @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response>responses);
}
