package com.pismo.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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
    private Date eventDate;

    @NotNull
    private float amount;

    @DBRef
    private Account account;

    @DBRef
    private OperationType operationType;

    public Transaction(@NotNull float amount, Account account, OperationType operationType) {
        this.amount = amount;
        this.account = account;
        this.operationType = operationType;
    }
}
