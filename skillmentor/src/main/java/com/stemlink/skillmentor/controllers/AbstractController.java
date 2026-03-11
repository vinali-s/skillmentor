package com.stemlink.skillmentor.controllers;

import com.stemlink.skillmentor.exceptions.SkillMentorException;
import org.springframework.http.HttpStatus;
import com.stemlink.skillmentor.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AbstractController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("An unexpected error occurred")
                .errorCode("INTERNAL_SERVER_ERROR")
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    Handles SkillMentor Exceptions
    @ExceptionHandler(SkillMentorException.class)
    public ResponseEntity<ErrorResponse> handleSkillMentorException(
            SkillMentorException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(ex.getStatus().toString())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .build();

        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
