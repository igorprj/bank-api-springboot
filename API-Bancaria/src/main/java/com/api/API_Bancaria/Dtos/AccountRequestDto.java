package com.api.API_Bancaria.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDto {

    @NotBlank(message = "Nome não pode ser vazio!")
    private String account_name;

    @NotBlank(message = "CPF não pode ser vazio!")
    @CPF(message = "CPF inválido!")
    private String account_cpf;

    @NotNull(message = "O saldo não pode ser nulo!")
    @Positive(message = "O saldo deve ser maior que 0!")
    private BigDecimal account_balance;

    @NotBlank(message = "Agência não pode ser vazia!")
    private String account_agency;

    @NotNull(message = "O número da conta não pode ser nulo!")
    private String number;
}
