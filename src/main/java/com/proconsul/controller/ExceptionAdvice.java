package com.proconsul.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.proconsul.exception.ErrorMessage;

@RestControllerAdvice
public class ExceptionAdvice {
	
	
	/*
	 * Inserendo l'annotation @ExceptionHandler(MethodArgumentNotValidException.class)
	 * sopra il metodo che abbiamo chiamato notValidExceptionHandler ogni volta che si
	 * verificherà la violazione di un vincolo di validazione sulla chiamata da parte
	 * di un Web Service Consumer la classe ExceptionAdvice (listener sui Controller
	 * dell'applicazione) rileverà l'eccezione "catchandola", interpretrà l'oggetto
	 * di risposta come un oggetto di eccezione grazie all'annotation @ExceptionHandler
	 * e restituirà nel JSON di risposta le proprietà dell'Oggetto ErrorMessage
	 * 
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ErrorMessage notValidExceptionHandler(Exception ex, WebRequest request) {
		
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), 
				ex.getMessage(), request.getDescription(false));
		
		return message;
		
	}
	
	/*
	 * Inserendo l'annotation @ExceptionHandler(Exception.class)
	 * sopra il metodo che abbiamo chiamato exceptionHandler ogni volta che si
	 * verificherà una Exception gestita nel codice dell'applicazione sulla chiamata da 
	 * parte di un Web Service Consumer la classe ExceptionAdvice (listener sui Controller
	 * dell'applicazione) rileverà l'eccezione "catchandola", interpretrà l'oggetto
	 * di risposta come un oggetto di eccezione grazie all'annotation @ExceptionHandler
	 * e restituirà nel JSON di risposta le proprietà dell'Oggetto ErrorMessage
	 * 
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ErrorMessage exceptionHandler(Exception ex, WebRequest request) {
		
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), 
				ex.getMessage(), request.getDescription(false));
		
		return message;
		
	}

}