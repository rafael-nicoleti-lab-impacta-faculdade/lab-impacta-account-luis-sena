package br.com.lab.impacta.account.handler;

import br.com.lab.impacta.account.handler.exception.AccountDontExistsException;
import br.com.lab.impacta.account.handler.exception.AccountWithoutBalanaceException;
import br.com.lab.impacta.account.handler.exception.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AccountDontExistsException.class)
    ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountDontExistsException exception){
        ErrorMessageResponse messageResponse = ErrorMessageResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestanp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(exception.getDescription())
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanaceException.class)
    ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountWithoutBalanaceException exception){
        ErrorMessageResponse messageResponse = ErrorMessageResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestanp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(exception.getDescription())
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.NOT_FOUND);
    }
}
