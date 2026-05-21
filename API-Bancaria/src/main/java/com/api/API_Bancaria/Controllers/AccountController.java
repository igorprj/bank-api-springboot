package com.api.API_Bancaria.Controllers;

import com.api.API_Bancaria.Dtos.AccountRequestDto;
import com.api.API_Bancaria.Dtos.AccountResponseDto;
import com.api.API_Bancaria.model.Account;
import com.api.API_Bancaria.services.Services;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Account")
public class AccountController {

    private final Services services;
    public AccountController(Services services) {
        this.services = services;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> ListAllAccounts(){
        return services.ListAllAccounts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto createAccount(@RequestBody AccountRequestDto accountdto) {
        AccountResponseDto accountResponseDto = services.create(accountdto);
        return accountResponseDto;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponseDto findAccountById(@PathVariable Long id){
        return services.FindbyId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountById(@PathVariable Long id){
        services.Delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponseDto updateAccountById(@PathVariable Long id, @RequestBody AccountRequestDto accountdto) {

        return services.UpdateAccount(accountdto, id);
    }
}
