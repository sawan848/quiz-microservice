package org.xyz.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.xyz.app.dto.QuestionErrorResponse;

import java.text.SimpleDateFormat;
import java.util.Date;




/**
*Nov 4, 20237:25:44 PM
* 
*/
@ControllerAdvice
public class GlobalExceptionalHandler {
	
	@ExceptionHandler
	public ResponseEntity<QuestionErrorResponse> handleException(QuizNotFoundException productNotFoundException){
		
		QuestionErrorResponse QuestionErrorResponse=	new QuestionErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				productNotFoundException.getMessage(),
				new SimpleDateFormat().format(new Date()));
		return new ResponseEntity<>(QuestionErrorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<QuestionErrorResponse> handleException(Exception exception){
		
		QuestionErrorResponse QuestionErrorResponse=	new QuestionErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				exception.getMessage(),
				new SimpleDateFormat().format(new Date()));

		return new ResponseEntity<>(QuestionErrorResponse,HttpStatus.BAD_REQUEST);
	}
	

}
