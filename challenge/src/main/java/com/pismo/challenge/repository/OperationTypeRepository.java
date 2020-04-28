package com.pismo.challenge.repository;

import com.pismo.challenge.model.OperationType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationTypeRepository extends MongoRepository<OperationType, String> {
}
