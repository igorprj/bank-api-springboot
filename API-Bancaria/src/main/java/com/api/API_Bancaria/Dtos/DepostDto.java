package com.api.API_Bancaria.Dtos;

import java.math.BigDecimal;

public record DepostDto(
        Long accountId,
        BigDecimal amount
) {
}
