package com.api.API_Bancaria.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDto {

    private Long account_Id;

    private String account_name;
    private String account_cpf;
    private BigDecimal account_balance;
    private String account_agency;
    private String number;
}
