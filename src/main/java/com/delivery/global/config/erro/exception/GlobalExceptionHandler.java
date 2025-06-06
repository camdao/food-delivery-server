package com.delivery.global.config.erro.exception;

import com.delivery.global.common.response.GlobalResponse;
import com.delivery.global.config.erro.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.of(ex.getClass().getSimpleName(), ex.getMessage());
        return super.handleExceptionInternal(ex, errorResponse, headers, statusCode, request);
    }

    /**
     * Occurs when a binding error occurs with javax.validation.Valid or @Validated. Occurs when
     * HttpMessageConverter registered in HttpMessageConverter binding fails. Occurs mainly
     * in @RequestBody, @RequestPart annotations.
     */
    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage(), e);
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), errorMessage);
        GlobalResponse response = GlobalResponse.fail(status.value(), errorResponse);
        return ResponseEntity.status(status).body(response);
    }

    /** Request Param Validation Exception Handling */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GlobalResponse> handleConstraintViolationException(
            ConstraintViolationException e) {
        log.error("ConstraintViolationException : {}", e.getMessage(), e);

        Map<String, Object> bindingErrors = new HashMap<>();
        e.getConstraintViolations()
                .forEach(
                        constraintViolation -> {
                            List<String> propertyPath =
                                    List.of(
                                            constraintViolation
                                                    .getPropertyPath()
                                                    .toString()
                                                    .split("\\."));
                            String path =
                                    propertyPath.stream()
                                            .skip(propertyPath.size() - 1L)
                                            .findFirst()
                                            .orElse(null);
                            bindingErrors.put(path, constraintViolation.getMessage());
                        });

        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), bindingErrors.toString());
        final GlobalResponse response =
                GlobalResponse.fail(HttpStatus.BAD_REQUEST.value(), errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Occurs when the types of PathVariable, RequestParam, RequestHeader, and RequestBody do not
     * match
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<GlobalResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException : {}", e.getMessage(), e);
        final ErrorCode errorCode = ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH;
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), errorCode.getMessage());
        final GlobalResponse response =
                GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /** Occurs when calling an unsupported HTTP method */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage(), e);
        final ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), errorCode.getMessage());
        final GlobalResponse response =
                GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /** CustomException exception handling */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GlobalResponse> handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getMessage(), e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse errorResponse =
                ErrorResponse.of(errorCode.name(), errorCode.getMessage());
        final GlobalResponse response =
                GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /** Handling 500 series errors */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<GlobalResponse> handleException(Exception e) {
        log.error("Internal Server Error : {}", e.getMessage(), e);
        final ErrorCode internalServerError = ErrorCode.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), internalServerError.getMessage());
        final GlobalResponse response =
                GlobalResponse.fail(internalServerError.getStatus().value(), errorResponse);
        return ResponseEntity.status(internalServerError.getStatus()).body(response);
    }
}
