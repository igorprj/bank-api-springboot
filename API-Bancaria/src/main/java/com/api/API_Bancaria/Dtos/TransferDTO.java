package com.api.API_Bancaria.Dtos;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferDTO(
        Long senderId,
        Long receiverId,
        @Positive
        BigDecimal amount
) {
}
