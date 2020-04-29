package com.pismo.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    @CreatedDate
    private Date eventDate = new Date();

    private float amount;

    private String accountId;

    private long operationTypeId;

    public Transaction(float amount, String accountId, long operationTypeId) {
        this.amount = amount;
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
    }
}
