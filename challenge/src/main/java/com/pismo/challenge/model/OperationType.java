package com.pismo.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "operation_types")
public class OperationType {

    @Transient
    public static final String SEQUENCE_NAME = "operation_sequence";

    @Id
    private long id;

    @NotNull
    private String description;

    public OperationType(long id, @NotNull String description) {
        this.id = id;
        this.description = description;
    }
}
