package com.api.API_Bancaria.Controllers;

import com.api.API_Bancaria.Dtos.DepositRequestDTO;
import com.api.API_Bancaria.Dtos.SaqueRequestDTO;
import com.api.API_Bancaria.Dtos.TransferDTO;
import com.api.API_Bancaria.Dtos.TransferRequestDTO;
import com.api.API_Bancaria.Repositories.TransactionRepository;
import com.api.API_Bancaria.services.TransactionServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/v1/transactions")
public class TransactionsController {


    private final TransactionServices transactionServices;

    public TransactionsController(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    public void deposit(@RequestBody @Valid DepositRequestDTO dto){
        transactionServices.deposit(dto);
    }

    @PostMapping("/saque")
    @ResponseStatus(HttpStatus.OK)
    public void saque(@RequestBody @Valid SaqueRequestDTO dto){
        transactionServices.saque(dto);
    }

    @PostMapping("/Transfer")
    @ResponseStatus(HttpStatus.OK)
    public void transfer(@RequestBody @Valid TransferDTO dto){
        transactionServices.transfer(dto);
    }
}
