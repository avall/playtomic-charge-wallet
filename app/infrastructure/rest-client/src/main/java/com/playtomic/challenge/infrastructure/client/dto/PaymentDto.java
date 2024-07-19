package com.playtomic.challenge.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class PaymentDto {

    @NonNull
    private String id;

    @JsonCreator
    public PaymentDto(@JsonProperty(value = "id", required = true) String id) {
        this.id = id;
    }
}
