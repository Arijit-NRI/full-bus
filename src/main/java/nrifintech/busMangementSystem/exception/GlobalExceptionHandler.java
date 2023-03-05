package nrifintech.busMangementSystem.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import nrifintech.busMangementSystem.payloads.ApiResponse;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResouceNotFound.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResouceNotFound ex){
		String msg = ex.getMessage();
		System.out.println(msg);
		ApiResponse apiResponse = new ApiResponse(msg, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnauthorizedAction.class)
	public ResponseEntity<ApiResponse> UnauthorizedActionHandler(UnauthorizedAction ex){
		String msg = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(msg, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.UNAUTHORIZED);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> argumentMethodException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<>();
		// listing errors and sending them
		ex.getBindingResult().getAllErrors().forEach(e->{
			resp.put(((FieldError)e).getField(), e.getDefaultMessage());
		});
		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}
}
