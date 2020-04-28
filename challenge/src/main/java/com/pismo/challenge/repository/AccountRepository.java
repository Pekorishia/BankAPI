package com.pismo.challenge.repository;

import com.pismo.challenge.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByDocumentNumber(String documentNumber);
}
