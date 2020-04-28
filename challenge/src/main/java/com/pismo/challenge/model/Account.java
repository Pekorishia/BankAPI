package com.pismo.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;

    @NotNull
    private String documentNumber;

    @NotNull
    private String name;

    public Account(String documentNumber, String name) {
        this.documentNumber = documentNumber;
        this.name = name;
    }
}
