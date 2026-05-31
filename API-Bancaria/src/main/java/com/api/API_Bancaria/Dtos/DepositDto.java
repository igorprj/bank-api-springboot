package com.api.API_Bancaria.Dtos;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositDto(
        Long accountId,
        @Positive(message = "O valor deve ser maior que zero!")
        BigDecimal amount
) {
}
