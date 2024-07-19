package org.xyz.app.exception;

/**
*Nov 4, 20237:25:44 PM
* 
*/
public class QuestionNotFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public QuestionNotFoundException(String message) {
		super(message);
	}
	public QuestionNotFoundException(Throwable cause) {
		super(cause);
	}
	public QuestionNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
}
