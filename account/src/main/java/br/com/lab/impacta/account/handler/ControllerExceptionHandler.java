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

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrorMessageResponse> accountNotFoundException(RuntimeException exception){
        var messageResponse = ErrorMessageResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestanp(LocalDateTime.now())
                .message(exception.getMessage())
                .description("Não possível processar sua requisição.")
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountDontExistsException.class)
    ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountDontExistsException exception){
        var messageResponse = ErrorMessageResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestanp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(exception.getDescription())
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanaceException.class)
    ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountWithoutBalanaceException exception){
        var messageResponse = ErrorMessageResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestanp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(exception.getDescription())
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }
}
