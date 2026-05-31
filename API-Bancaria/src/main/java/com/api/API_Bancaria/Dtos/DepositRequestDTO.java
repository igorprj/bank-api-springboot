package com.api.API_Bancaria.Dtos;

import java.math.BigDecimal;

public record DepositRequestDTO(
        Long accountId,
        BigDecimal amount
) {
}
