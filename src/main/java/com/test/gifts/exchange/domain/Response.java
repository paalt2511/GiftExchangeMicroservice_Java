package com.test.gifts.exchange.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    public Status status;
    private String description;

    public enum Status {
        SUCCESS, FAILURE
    }
}
