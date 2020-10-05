package com.adambaku.githubapi.configuration;

import com.adambaku.githubapi.common.exception.ApplicationExceptionAbstract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler
{
    /**
     * Simple REST exception handler
     *
     * It builds a custom error response while an application exception occurred
     *
     * {@link ApplicationExceptionAbstract} is a base custom type of runtime exception
     * which may be thrown inside the package
     */
    @ExceptionHandler({ ApplicationExceptionAbstract.class })
    public final ResponseEntity<?> handleException(ApplicationExceptionAbstract exception, WebRequest request)
    {
        //this part can be moved to builder object with separate responsibility
        ApiResponse response = new ApiResponse();
        response.message = exception.getMessage();
        response.type = exception.getCode().toString();
        response.details = exception.getData();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Simple api error structure
     */
    protected final class ApiResponse
    {
        public String message;
        public String type;
        public Map<String, String> details;
    }
}
