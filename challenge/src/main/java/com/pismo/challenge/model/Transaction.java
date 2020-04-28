package com.pismo.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    @NotNull
    private float amount;

    @NotNull
    private String eventDate;

    @DBRef
    private String account;

    @DBRef
    private String operationType;

    public Transaction(@NotNull float amount, @NotNull String eventDate, String account, String operationType) {
        this.amount = amount;
        this.eventDate = eventDate;
        this.account = account;
        this.operationType = operationType;
    }
}
