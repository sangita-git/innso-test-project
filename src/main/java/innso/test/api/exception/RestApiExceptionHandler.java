package innso.test.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/* This class handles all the Exceptions 
 * thrown through out the application
 * */
@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

	//method to handle all Exceptions
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAllExceptions(
			Exception exception, WebRequest request) {
		String error = exception.getMessage();
		return buildResponseEntity(
				new RestApiError(
						HttpStatus.INTERNAL_SERVER_ERROR, 
						error, exception));
	}
	
	/*
	 * Build ResponseEntity with Custom
	 * Error Object (RestApiError) containing 
	 * Exception information
	 */	
	private ResponseEntity<Object> buildResponseEntity(RestApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}