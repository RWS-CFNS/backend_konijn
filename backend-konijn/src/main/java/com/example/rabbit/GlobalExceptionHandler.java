package com.example.rabbit;

import org.springframework.web.bind.annotation.ControllerAdvice;

//TODO handle frontend exceptions in this class

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler({StudentNotFoundException.class})
//    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException exception) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(exception.getMessage());
//    }
//    @ExceptionHandler({StudentAlreadyExistsException.class})
//    public ResponseEntity<Object> handleStudentAlreadyExistsException(StudentAlreadyExistsException exception) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(exception.getMessage());
//    }
//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(exception.getMessage());
//    }
}
