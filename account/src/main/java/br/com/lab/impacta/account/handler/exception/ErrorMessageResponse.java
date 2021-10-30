package br.com.lab.impacta.account.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorMessageResponse {

    private int statusCode;
    private LocalDateTime timestanp;
    private String message;
    private String description;

}
