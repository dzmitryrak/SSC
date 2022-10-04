package com.itechart.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseStatus {
    int statusCode;
    boolean success;
    String id;
    String[] errors;
}
