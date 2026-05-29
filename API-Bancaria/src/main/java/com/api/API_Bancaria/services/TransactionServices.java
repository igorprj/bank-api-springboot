package com.api.API_Bancaria.services;

import com.api.API_Bancaria.Dtos.DepostDto;
import com.api.API_Bancaria.Dtos.SaqueDTO;
import com.api.API_Bancaria.Exceptions.AccountNotFoundException;
import com.api.API_Bancaria.Exceptions.InsufficientBalanceException;
import com.api.API_Bancaria.Repositories.AccountRepository;
import com.api.API_Bancaria.Repositories.TransactionRepository;
import com.api.API_Bancaria.model.Account;
import com.api.API_Bancaria.model.Transaction;
import com.api.API_Bancaria.model.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServices {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public TransactionServices(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void deposit(DepostDto depostDto) {
        Account account = accountRepository
                .findById(depostDto.accountId())
                .orElseThrow(() -> new AccountNotFoundException("Conta não encontrada!"));

        account.setAccount_balance(
                account.getAccount_balance().add(depostDto.amount())
        );

        Transaction transaction = new Transaction();

        transaction.setAmount(depostDto.amount());

        transaction.setType(TransactionType.DEPOSIT);

        transaction.setTimestamp(LocalDateTime.now());

        transaction.setReceiveraccount(account);

        transactionRepository.save(transaction);

        accountRepository.save(account);

    }

    @Transactional
    public void saque(SaqueDTO saqueDTO) {
        Account account = accountRepository
                .findById(saqueDTO.accountId())
                .orElseThrow(() -> new AccountNotFoundException("Conta não encontrada!"));

        if (saqueDTO.amount().compareTo(account.getAccount_balance()) > 0 ) {
            throw new InsufficientBalanceException("Saldo insuficiente!");
        }

        account.setAccount_balance(
                account.getAccount_balance().subtract(saqueDTO.amount())
        );

        Transaction transaction = new Transaction();
        transaction.setAmount(saqueDTO.amount());
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setSenderacoount(account);

        transactionRepository.save(transaction);
        accountRepository.save(account);
    }
}
