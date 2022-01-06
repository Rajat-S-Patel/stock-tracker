package com.example.stocktracker.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    public static String ERROR_TYPE="error";
    public static String SUCCESS_TYPE="success";
    private String type;
    private String message;

}
