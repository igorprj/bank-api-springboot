package com.api.API_Bancaria.Controllers;

import com.api.API_Bancaria.Dtos.AccountRequestDto;
import com.api.API_Bancaria.Dtos.AccountResponseDto;
import com.api.API_Bancaria.model.Account;
import com.api.API_Bancaria.services.AccountServices;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Account")
public class AccountController {

    private final AccountServices services;
    public AccountController(AccountServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> ListAllAccounts(){
        return ResponseEntity.ok(
                services.ListAllAccounts()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto createAccount(@RequestBody @Valid AccountRequestDto accountdto) {
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
    public AccountResponseDto updateAccountById(@PathVariable Long id, @RequestBody @Valid AccountRequestDto accountdto) {

        return services.UpdateAccount(accountdto, id);
    }
}
