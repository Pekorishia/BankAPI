package com.pismo.challenge.controller.v1;

import com.pismo.challenge.dto.AccountDto;
import com.pismo.challenge.model.Account;
import com.pismo.challenge.model.Account;
import com.pismo.challenge.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        try {
            List<Account> accounts = accountService.getAllAccounts();

            if (accounts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<AccountDto> accountDtos = accounts.stream()
                    .map(account -> modelMapper.map(account, AccountDto.class))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(accountDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") String id) {
        Optional<Account> account = accountService.findById(id);

        if(account.isPresent()) {
            AccountDto accountDto = modelMapper.map(account.get(), AccountDto.class);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/document-number/{documentNumber}")
    public ResponseEntity<AccountDto> getAccountByDocumentNumber(@PathVariable("documentNumber") String documentNumber) {
        Optional<Account> account = accountService.findByDocumentNumber(documentNumber);

        if(account.isPresent()) {
            AccountDto accountDto = modelMapper.map(account.get(), AccountDto.class);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto newAccountDto) {
        Account receivedAccount = modelMapper.map(newAccountDto, Account.class);
        System.out.println(receivedAccount);

        try {
            Account createdAccount = accountService.save(new Account(receivedAccount.getDocumentNumber(), receivedAccount.getName()));
            return new ResponseEntity<>(modelMapper.map(createdAccount, AccountDto.class), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") String id, @RequestBody AccountDto newAccountDto) {
        Account receivedAccount = modelMapper.map(newAccountDto, Account.class);
        Optional<Account> account = accountService.findById(id);

        if (account.isPresent()) {
            Account currentAccount = account.get();

            currentAccount.setName(receivedAccount.getName());
            currentAccount.setDocumentNumber(receivedAccount.getDocumentNumber());

            accountService.save(currentAccount);

            return new ResponseEntity<>(modelMapper.map(currentAccount, AccountDto.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable("id") String id) {
        try {
            accountService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
