package com.pismo.challenge.controller.v1;

import com.pismo.challenge.dto.TransactionDto;
import com.pismo.challenge.model.Transaction;
import com.pismo.challenge.model.Transaction;
import com.pismo.challenge.repository.TransactionRepository;
import com.pismo.challenge.service.TransactionService;
import com.pismo.challenge.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();

            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<TransactionDto> transactionDtos = transactions.stream()
                    .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") String id) {
        Optional<Transaction> transaction = transactionService.findById(id);

        if(transaction.isPresent()) {
            TransactionDto transactionDto = modelMapper.map(transaction.get(), TransactionDto.class);
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto newTransactionDto) {
        Transaction receivedTransaction = modelMapper.map(newTransactionDto, Transaction.class);

        try {
            Transaction createdTransaction = transactionService.save(new Transaction(receivedTransaction.getAmount(), receivedTransaction.getAccountId(), receivedTransaction.getOperationTypeId()));

            return new ResponseEntity<>(modelMapper.map(createdTransaction, TransactionDto.class), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable("id") String id, @RequestBody TransactionDto newTransactionDto) {
        Transaction receivedTransaction = modelMapper.map(newTransactionDto, Transaction.class);
        Optional<Transaction> transaction = transactionService.findById(id);

        if (transaction.isPresent()) {
            Transaction currentTransaction = transaction.get();

            currentTransaction.setAmount(receivedTransaction.getAmount());
            currentTransaction.setAccountId(receivedTransaction.getAccountId());;
            currentTransaction.setOperationTypeId(receivedTransaction.getOperationTypeId());

            transactionService.save(currentTransaction);

            return new ResponseEntity<>(modelMapper.map(currentTransaction, TransactionDto.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionDto> deleteTransaction(@PathVariable("id") String id) {
        try {
            transactionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
