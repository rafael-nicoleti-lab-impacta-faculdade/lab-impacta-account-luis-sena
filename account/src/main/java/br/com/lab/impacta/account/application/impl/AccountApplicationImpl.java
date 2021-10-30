package br.com.lab.impacta.account.application.impl;

import br.com.lab.impacta.account.application.AccountApplication;
import br.com.lab.impacta.account.application.adapter.AccountAdapter;
import br.com.lab.impacta.account.application.dto.request.DebitAccountRequest;
import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse;
import br.com.lab.impacta.account.application.dto.response.DebitAccountResponse;
import br.com.lab.impacta.account.model.Account;
import br.com.lab.impacta.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountApplicationImpl implements AccountApplication {

    private final AccountService service;

    @Override
    public AccountBalanceResponse balance(Long accountId) {
        Account account = service.findAccount(accountId);
        return AccountAdapter.toDtoBalance(account);
    }

    @Override
    public DebitAccountResponse debit(Long accountId, DebitAccountRequest accountRequest) {
        service.debitAccount(accountId, accountRequest.getValueOfDebit());
        return new DebitAccountResponse(true);
    }
}
