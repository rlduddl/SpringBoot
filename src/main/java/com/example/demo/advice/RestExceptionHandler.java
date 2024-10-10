package com.example.demo.advice;

import javax.management.BadAttributeValueExpException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.exception.BadRequestException;
import com.example.demo.payload.response.ApiResponse;

@RestControllerAdvice
public class RestExceptionHandler {
	
	// 요청한 URL을 리턴한다.
	private String getPathFromWebRequest(WebRequest request) {
		try {
			return ((ServletWebRequest) request).getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
		} catch(Exception e) {
			return null;
		}
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody()
	public ApiResponse handleBadRequestException(BadRequestException ex, WebRequest request) {
		return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), getPathFromWebRequest(request));
	}

}
