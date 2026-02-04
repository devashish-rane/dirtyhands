package com.devashish.dirtyhands.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devashish.dirtyhands.common.ApiError;
import com.devashish.dirtyhands.common.ApiResponse;
import com.devashish.dirtyhands.common.RequestIdUtil;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class UserExceptionHandler {
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(
        MethodArgumentNotValidException ex,
        HttpServletRequest request
    ){
        Map<String,String> errors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (a, b) -> a));
        ApiError error = new ApiError("VALIDATION_FAILED", "Invalid request", errors);
        String requestId = RequestIdUtil.getRequestId(request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(error, requestId));
    }
}
