package com.api.API_Bancaria.services;

import com.api.API_Bancaria.Dtos.AccountRequestDto;
import com.api.API_Bancaria.Dtos.AccountResponseDto;
import com.api.API_Bancaria.Repositories.AccountRepository;
import com.api.API_Bancaria.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Services {

    private final AccountRepository accountRepository;

    public Services(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> ListAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public AccountResponseDto create(AccountRequestDto accountdto){

        Account account = new Account();
        account.setAccount_name(accountdto.getAccount_name());
        account.setAccount_cpf(accountdto.getAccount_cpf());
        account.setAccount_agency(accountdto.getAccount_agency());
        account.setAccount_balance(accountdto.getAccount_balance());
        account.setNumber(accountdto.getNumber());

        accountRepository.save(account);

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccount_name(account.getAccount_name());
        accountResponseDto.setAccount_cpf(account.getAccount_cpf());
        accountResponseDto.setAccount_agency(account.getAccount_agency());
        accountResponseDto.setAccount_balance(account.getAccount_balance());
        accountResponseDto.setNumber(account.getNumber());

        return accountResponseDto;
    }

    public AccountResponseDto FindbyId(Long id){
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        Account account = accountRepository.findById(id).get();
        accountResponseDto.setAccount_name(account.getAccount_name());
        accountResponseDto.setAccount_cpf(account.getAccount_cpf());
        accountResponseDto.setAccount_agency(account.getAccount_agency());
        accountResponseDto.setAccount_balance(account.getAccount_balance());
        accountResponseDto.setNumber(account.getNumber());

        return accountResponseDto;
    }

    public void Delete(Long id){
        accountRepository.deleteById(id);
    }

    public AccountResponseDto UpdateAccount(AccountRequestDto accountdto,Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        account.setAccount_Id(account.getAccount_Id());
        account.setAccount_name(accountdto.getAccount_name());
        account.setAccount_cpf(accountdto.getAccount_cpf());
        account.setAccount_agency(accountdto.getAccount_agency());
        account.setAccount_balance(accountdto.getAccount_balance());
        account.setNumber(accountdto.getNumber());

        accountRepository.save(account);

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccount_name(account.getAccount_name());
        accountResponseDto.setAccount_cpf(account.getAccount_cpf());
        accountResponseDto.setAccount_agency(account.getAccount_agency());
        accountResponseDto.setAccount_balance(account.getAccount_balance());
        accountResponseDto.setNumber(account.getNumber());

        return accountResponseDto;
    }
}
