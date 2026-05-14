package com.api.API_Bancaria.services;

import com.api.API_Bancaria.Dtos.AccountRequestDto;
import com.api.API_Bancaria.Dtos.AccountResponseDto;
import com.api.API_Bancaria.Repositories.AccountRepository;
import com.api.API_Bancaria.model.Account;
import org.springframework.stereotype.Service;

@Service
public class service {

    private AccountRepository accountRepository;

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public AccountResponseDto (AccountRequestDto dto){

        Account account = new Account();
        account.setAccount_name(dto.getAccount_name());
        account.setAccount_cpf(dto.getAccount_cpf());
        account.setAccount_agency(dto.getAccount_agency());
        account.setAccount_balance(dto.getAccount_balance());
        account.setNumber(dto.getNumber());

        accountRepository.save(account);

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccount_name(account.getAccount_name());
        accountResponseDto.setAccount_agency(dto.getAccount_agency());
        accountResponseDto.setAccount_balance(account.getAccount_balance());
        accountResponseDto.setNumber(account.getNumber());

        return accountResponseDto;
    }
}
