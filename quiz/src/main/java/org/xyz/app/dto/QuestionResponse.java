package org.xyz.app.dto;

/**
 * 8/21/2023
 * 2:43 PM
 */

public record QuestionResponse(

     int id,
     String questionTitle,
     String option1,
     String option2,
     String option3)
    {
}
