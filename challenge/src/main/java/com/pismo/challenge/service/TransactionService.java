package com.pismo.challenge.service;

import com.pismo.challenge.model.Transaction;
import com.pismo.challenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        List<Transaction> accounts = new ArrayList<Transaction>();

        transactionRepository.findAll().forEach(accounts::add);

        return accounts;
    }

    public Optional<Transaction> findById(String id) {
        return transactionRepository.findById(id);
    }

    public Transaction save(Transaction account) {
        return transactionRepository.save(account);
    }

    public void deleteById(String id) {
        transactionRepository.deleteById(id);
    }
}
