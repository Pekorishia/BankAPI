package com.pismo.challenge.service;

import com.pismo.challenge.model.Account;
import com.pismo.challenge.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();

        accountRepository.findAll().forEach(accounts::add);

        return accounts;
    }

    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    public Optional<Account> findByDocumentNumber(String documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber);
    }
}
