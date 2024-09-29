package org.xyz.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 6/26/2023
 * 10:49 AM
 */
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String questionTitle;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String rightAnswer;
    private String difficultyLevel;



    public Question( ) { }
    public Question( String questionTitle, String category, String option1, String option2, String option3, String rightAnswer, String difficultyLevel) {
        this.questionTitle = questionTitle;
        this.category = category;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.rightAnswer = rightAnswer;
        this.difficultyLevel = difficultyLevel;
    }
      public Question(int id, String questionTitle, String category, String option1, String option2, String option3, String rightAnswer, String difficultyLevel) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.category = category;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.rightAnswer = rightAnswer;
        this.difficultyLevel = difficultyLevel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", category='" + category + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                '}';
    }
}
