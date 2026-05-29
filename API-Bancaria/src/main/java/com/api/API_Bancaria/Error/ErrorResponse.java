package com.api.API_Bancaria.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    public String message;
    public int  status;
    public LocalDateTime Timestamp;


    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.Timestamp = LocalDateTime.now();
    }


}
