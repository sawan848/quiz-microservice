package org.xyz.app.dto;

/**
 * 8/21/2023
 * 2:43 PM
 */
//@Data
//@NoArgsConstructor
public record QuestionResponse(

     int id,
     String questionTitle,
     String option1,
     String option2,
     String option3)
    {

//    public QuestionWrapper(int id, String questionTitle, String option1, String option2, String option3) {
//        this.id = id;
//        this.questionTitle = questionTitle;
//        this.option1 = option1;
//        this.option2 = option2;
//        this.option3 = option3;
//    }
}
