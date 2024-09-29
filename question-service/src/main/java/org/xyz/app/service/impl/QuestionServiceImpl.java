package org.xyz.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.xyz.app.dto.QuestionResponse;
import org.xyz.app.dto.Response;
import org.xyz.app.exception.QuestionNotFoundException;
import org.xyz.app.model.Question;
import org.xyz.app.repositry.QuestionRepository;
import org.xyz.app.service.QuestionService;
import org.xyz.app.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;


/**
 * 6/26/2023
 * 11:00 AM
 */
@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final KafkaTemplate<String,Question>kafkaTemplate;
    @Value("${spring.kafka.template.default-topic}")
    private   String topic;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, KafkaTemplate<String, Question> kafkaTemplate) {
        this.questionRepository = questionRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public  Question saveQuestion(Question question) {
        try {
            log.info("Save Question {} into database!!",question.getId());
            System.out.println("question = " + question);
            System.out.println("topic = " + topic);
            Question savedQuestion=questionRepository.save(question);
            kafkaTemplate.send(Constants.TOPIC_NAME,savedQuestion);
            return savedQuestion;
        } catch (QuestionNotFoundException e) {
            log.error(" Question {} is not save into database!! ",question.getId());
            throw new QuestionNotFoundException("Question is not inserted into a database!!");
        }
    }

    @Override
    public  List<Question> allQuestion() {
        try {
            log.info("fetch all Question from database!!");
            return questionRepository.findAll();

        } catch (QuestionNotFoundException e) {
            log.error("Database is Empty!! ");
            throw new QuestionNotFoundException("There is not Questions in the database!!, Database is Empty!!");
        }
    }

    @Override
    public  List<Question> getQuestionByCategory(String category) {
        try {
            log.info("fetch Question with category {} !! ",category);
            return (questionRepository.findByCategory(category));

        } catch (QuestionNotFoundException e) {
            log.error("There is not Question found with category {} !! ",category);

            throw new QuestionNotFoundException("There is not Question in the database by the category '"+category+"', Database is Empty!!");
        }
    }

    @Override
    public String deleteQuestionById(int id) {


            Question question = questionRepository.findById(id).orElse(null);
            if (question != null) {
                questionRepository.deleteById(id);
                log.info("Question {} is in database!! ",id);
                return ("Deleted Successfully");
            } else {
                log.error(" Question {} is not found in database!! ",id);
                throw new QuestionNotFoundException("There is no Question "+id+" in the database!!, Database is Empty!!");

            }



    }
    @Override
    public Question updateQuestionById(int id, Question question) {

        Question question1 = questionRepository.findById(id).orElse(null);
        if (question1 != null) {
            question1.setDifficultyLevel(question.getDifficultyLevel());
            question1.setCategory(question.getCategory());
            question1.setQuestionTitle(question.getQuestionTitle());
            log.error("Question {} is  in database!! ",id);
            return saveQuestion(question);
        } else {
            log.error(" Question {} is not found in database!! ",id);

            throw new QuestionNotFoundException("There is no Questions in the database!!, Database is Empty!!");

        }

    }

    @Override
    public List<Question> getQuestionByDifficultyLevel(String difficultyLevel) {
        try {

            List<Question> byDifficultyLevel = questionRepository.findByDifficultyLevel(difficultyLevel.toUpperCase(Locale.ENGLISH));
            if (byDifficultyLevel!=null){
                log.info("Get Question by difficulty level {} !! ",difficultyLevel);
                return byDifficultyLevel;
            }

            throw new QuestionNotFoundException("There is no Questions in the database!!, Database is Empty!!");
        } catch (QuestionNotFoundException e) {
            log.error("There is no  Question {} with level  in database!! ",difficultyLevel);
            throw new QuestionNotFoundException("There is no Questions in the database!!, Database is Empty!!");
        }
    }


    @Override
    public List<Integer> getQuestionForQuiz(String category, Integer noOfQuestion) {
        List<Integer> questionList=questionRepository.findRandomQuestionsByCategory(category,noOfQuestion);
        log.info("Get Question for Quiz with category {}  and no of question is {} !! ",category,noOfQuestion);

        return (questionList);
    }

    @Override
    public List<QuestionResponse> getQuestionFromId(List<Integer> questionIds) {
        List<QuestionResponse>wrappers=new ArrayList<>();
        List<Question>questionsList=new ArrayList<>();

        for (Integer id:questionIds){
            questionsList.add(questionRepository.findById(id).get());
        }

        for (Question question:questionsList){

            QuestionResponse wrapper=new QuestionResponse(
                                        question.getId(),question.getQuestionTitle(),
                                        question.getOption1(),question.getOption2(),
                                        question.getOption3());

            wrappers.add(wrapper);

        }

        return wrappers;
    }

    @Override
    public Integer getScore(List<Response> responses) {

        int score=0;
        for (Response response:responses){
            Question question=questionRepository.findById(response.id()).get();
            if (response.response().equals(question.getRightAnswer())){
                score++;
            }
        }
        log.error("Get the result of the of  ");

        return score;
    }
}
