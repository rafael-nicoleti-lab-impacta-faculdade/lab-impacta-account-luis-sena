package br.com.lab.impacta.account.service.impl;

import br.com.lab.impacta.account.config.properties.AccountExceptionsProperties;
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
    private final AccountExceptionsProperties messages;

    @Override
    public Account findAccount(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() ->
                new AccountDontExistsException(messages.getAccountDontExistsMessage(),
                        messages.getAccountDontExistsDescription()));
    }

    @Override
    public void debitAccount(Long accountId, Double valueOfDebit) {
        var account = this.findAccount(accountId);
        var debited = account.debit(valueOfDebit);

        if(!debited)
            throw new AccountWithoutBalanaceException(messages.getAccountWithoutBalanceMessage(),
                    messages.getAccountWithoutBalanceDescription());

        this.repository.save(account);
    }
}
