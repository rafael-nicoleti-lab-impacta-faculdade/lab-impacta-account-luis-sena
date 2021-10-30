package br.com.lab.impacta.account.handler.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountWithoutBalanaceException extends RuntimeException {

    private String description;

    public AccountWithoutBalanaceException(String message, String description) {
        super(message);
        this.description = description;
    }
}
