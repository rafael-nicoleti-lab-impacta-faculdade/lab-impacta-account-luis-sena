package br.com.lab.impacta.account.service.impl;

import br.com.lab.impacta.account.handler.exception.AccountDontExistsException;
import br.com.lab.impacta.account.handler.exception.AccountWithoutBalanaceException;
import br.com.lab.impacta.account.model.Account;
import br.com.lab.impacta.account.repository.AccountRepository;
import br.com.lab.impacta.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Value("${lab.account.exceptions.account-dont-exists-message}")
    private String messageExceptionAccountDontExists;

    @Value("${lab.account.exceptions.account-dont-exists-description}")
    private String messageExceptionAccountDontExistsDescription;

    @Value("${lab.account.exceptions.account-without-balance-message}")
    private String messageExceptionAccountWithoutBalance;

    @Value("${lab.account.exceptions.account-without-balance-message}")
    private String messageExceptionAccountWithoutBalanceDescription;

    @Override
    public Account findAccount(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() ->
                new AccountDontExistsException(messageExceptionAccountDontExists, messageExceptionAccountDontExistsDescription));
    }

    @Override
    public void debitAccount(Long accountId, Double valueOfDebit) {
        Account account = this.findAccount(accountId);

        boolean debited = account.debit(valueOfDebit);

        if(!debited)
            throw new AccountWithoutBalanaceException(messageExceptionAccountWithoutBalance, messageExceptionAccountWithoutBalanceDescription);

        this.repository.save(account);
    }
}
