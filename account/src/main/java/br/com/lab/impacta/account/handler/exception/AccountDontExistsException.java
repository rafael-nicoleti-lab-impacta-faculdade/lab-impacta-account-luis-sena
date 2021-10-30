package br.com.lab.impacta.account.handler.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountDontExistsException extends RuntimeException {
    private String description;

    public AccountDontExistsException(String message, String description) {
        super(message);
        this.description = description;
    }
}
