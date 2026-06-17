package com.api.API_Bancaria.services;

import com.api.API_Bancaria.Dtos.AccountRequestDto;
import com.api.API_Bancaria.Dtos.AccountResponseDto;
import com.api.API_Bancaria.Exceptions.AccountNotFoundException;
import com.api.API_Bancaria.Repositories.AccountRepository;
import com.api.API_Bancaria.model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServices {

    private final AccountRepository accountRepository;

   private final PasswordEncoder passwordEncoder;

    public AccountServices(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AccountResponseDto> ListAllAccounts(){
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream()
                .map(account -> {
                    AccountResponseDto dto = new AccountResponseDto();
                           dto.setAccount_id(account.getAccount_Id());
                           dto.setAccount_name(account.getAccount_name());
                           dto.setAccount_cpf(account.getAccountcpf());
                           dto.setAccount_balance(account.getAccount_balance());
                           dto.setAccount_agency(account.getAccount_agency());
                           dto.setNumber(account.getNumber());

                           return dto;

                })
                .toList();

    }

    public AccountResponseDto create(AccountRequestDto accountdto){

        Account account = new Account();
        account.setAccount_name(accountdto.getAccount_name());
        account.setAccountcpf(accountdto.getAccount_cpf());
        account.setAccount_agency(accountdto.getAccount_agency());
        account.setAccount_balance(accountdto.getAccount_balance());
        account.setNumber(accountdto.getNumber());
        account.setPassword(
                passwordEncoder.encode(accountdto.getPassword()));

        accountRepository.save(account);

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccount_name(account.getAccount_name());
        accountResponseDto.setAccount_cpf(account.getAccountcpf());
        accountResponseDto.setAccount_agency(account.getAccount_agency());
        accountResponseDto.setAccount_balance(account.getAccount_balance());
        accountResponseDto.setNumber(account.getNumber());

        return accountResponseDto;
    }

    public AccountResponseDto FindbyId(Long id){
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Conta não encontrada!"));
        accountResponseDto.setAccount_name(account.getAccount_name());
        accountResponseDto.setAccount_cpf(account.getAccountcpf());
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
                .orElseThrow(() -> new AccountNotFoundException("Conta não encontrada!"));
        account.setAccount_Id(account.getAccount_Id());
        account.setAccount_name(accountdto.getAccount_name());
        account.setAccountcpf(accountdto.getAccount_cpf());
        account.setAccount_agency(accountdto.getAccount_agency());
        account.setAccount_balance(accountdto.getAccount_balance());
        account.setNumber(accountdto.getNumber());

        accountRepository.save(account);

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccount_name(account.getAccount_name());
        accountResponseDto.setAccount_cpf(account.getAccountcpf());
        accountResponseDto.setAccount_agency(account.getAccount_agency());
        accountResponseDto.setAccount_balance(account.getAccount_balance());
        accountResponseDto.setNumber(account.getNumber());

        return accountResponseDto;
    }
}
