package org.xyz.app.exception;

/**
*Nov 4, 20237:25:44 PM
* 
*/
public class QuizNotFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public QuizNotFoundException(String message) {
		super(message);
	}
	public QuizNotFoundException(Throwable cause) {
		super(cause);
	}
	public QuizNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
}
