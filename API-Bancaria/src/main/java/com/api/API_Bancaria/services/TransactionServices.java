package com.api.API_Bancaria.services;

import com.api.API_Bancaria.Dtos.*;
import com.api.API_Bancaria.Exceptions.AccountNotFoundException;
import com.api.API_Bancaria.Exceptions.InsufficientBalanceException;
import com.api.API_Bancaria.Repositories.AccountRepository;
import com.api.API_Bancaria.Repositories.TransactionRepository;
import com.api.API_Bancaria.model.Account;
import com.api.API_Bancaria.model.Transaction;
import com.api.API_Bancaria.model.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void deposit(DepositRequestDTO depositDto) {
        Account account = accountRepository
                .findById(depositDto.accountId())
                .orElseThrow(() -> new AccountNotFoundException("Conta não encontrada!"));

        account.setAccount_balance(
                account.getAccount_balance().add(depositDto.amount())
        );

        Transaction transaction = new Transaction();

        transaction.setAmount(depositDto.amount());

        transaction.setType(TransactionType.DEPOSIT);

        transaction.setTimestamp(LocalDateTime.now());

        transaction.setReceiveraccount(account);

        transactionRepository.save(transaction);

        accountRepository.save(account);

    }

    @Transactional
    public void saque(SaqueRequestDTO saqueDTO) {
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

    @Transactional
    public void transfer(TransferDTO transferDTO) {
        Account sender = accountRepository
                .findById(transferDTO.senderId())
                .orElseThrow(() -> new AccountNotFoundException("Conta não encontrada!"));

        Account receiver = accountRepository
                .findById(transferDTO.receiverId())
                .orElseThrow(() -> new AccountNotFoundException("Conta não encontrada!"));

        if (transferDTO.senderId().equals(transferDTO.receiverId())) {
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta!");
        }

        if (transferDTO.amount().compareTo(sender.getAccount_balance()) > 0 ) {
            throw new InsufficientBalanceException("Saldo insuficiente!");
        }

        sender.setAccount_balance(sender.getAccount_balance().subtract(transferDTO.amount()));

        receiver.setAccount_balance(receiver.getAccount_balance().add(transferDTO.amount()));

        Transaction transaction = new Transaction();
        transaction.setAmount(transferDTO.amount());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setSenderacoount(sender);
        transaction.setReceiveraccount(receiver);
        transactionRepository.save(transaction);
        accountRepository.save(sender);
        accountRepository.save(receiver);

    }
}
