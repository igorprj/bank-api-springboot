package com.api.API_Bancaria.Dtos;

import java.math.BigDecimal;

public record TransferRequestDTO(
        long id,
        long receiverid,
        BigDecimal amount
) {


}
