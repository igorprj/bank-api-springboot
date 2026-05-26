package com.api.API_Bancaria.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    public String message;
    public int  status;
    public LocalDateTime Timestamp;


    public ErrorResponse(String message, int value) {
    }
}
