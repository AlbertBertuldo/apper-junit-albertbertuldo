package com.gcash;

import java.util.Optional;

public class BalanceService {

    private final AccountRepository accountRepository;

    public BalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Double getBalance(String id) {
        Optional<Account> account = Optional.ofNullable(accountRepository.getAccount(id));
        if (account.isPresent()) {
            return account.get().getBalance();
        } else {
            return null;
        }
    }

    public void debit(String id, Double amount) {
        Optional<Account> account = Optional.ofNullable(accountRepository.getAccount(id));
        if (account.isPresent()) {
            account.get().setBalance(account.get().getBalance() - amount);
        }
    }

    public void credit(String id, Double amount) {
        Optional<Account> account = Optional.ofNullable(accountRepository.getAccount(id));
        if (account.isPresent()) {
            account.get().setBalance(account.get().getBalance() + amount);
        }
    }

    public void transfer(String from, String to, Double amount) {
        debit(from, amount);
        credit(to, amount);
    }

}
