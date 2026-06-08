package com.api.API_Bancaria.Services;

import com.api.API_Bancaria.Dtos.DepositRequestDTO;
import com.api.API_Bancaria.Dtos.SaqueRequestDTO;
import com.api.API_Bancaria.Dtos.TransferDTO;
import com.api.API_Bancaria.Dtos.TransferRequestDTO;
import com.api.API_Bancaria.Exceptions.AccountNotFoundException;
import com.api.API_Bancaria.Exceptions.InsufficientBalanceException;
import com.api.API_Bancaria.Repositories.AccountRepository;
import com.api.API_Bancaria.Repositories.TransactionRepository;
import com.api.API_Bancaria.model.Account;
import com.api.API_Bancaria.model.Transaction;
import com.api.API_Bancaria.services.TransactionServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServices transactionServices;

    @Test
    void shouldDepositMoneySuccessfully() {
        Account account = new Account();
        account.setAccount_balance(
                new BigDecimal("1000")
        );

        DepositRequestDTO dto =
                new DepositRequestDTO(
                        1L,
                        new BigDecimal("200")
                );


        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        transactionServices.deposit(dto);

        assertEquals(
                new BigDecimal("1200"),
                account.getAccount_balance()
        );

        verify(accountRepository).save(account);

        verify(transactionRepository)
            .save(any(Transaction.class));
    }

    @Test
    void shouldWithdrawMoneySuccessfully() {
        Account account = new Account();
        account.setAccount_balance(
                new BigDecimal("1000")
        );

        SaqueRequestDTO dto =
                new SaqueRequestDTO(
                        1L,
                        new BigDecimal("200")
                );

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        transactionServices.saque(dto);

        assertEquals(
                new BigDecimal("800"),
                account.getAccount_balance()
        );

        verify(accountRepository).save(account);

        verify(transactionRepository)
                .save(any(Transaction.class));
    }

    @Test
    void shouldTransferMoneySuccessfully() {
        Account account = new Account();
        account.setAccount_balance(
                new BigDecimal("1000")
        );

        Account account2 = new Account();
        account2.setAccount_balance(
                new BigDecimal("200")
        );

        TransferDTO dto =
                new TransferDTO(
                        1L,
                        2L,
                        new BigDecimal("200")
                );


        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        when(accountRepository.findById(2L))
                .thenReturn(Optional.of(account2));

        transactionServices.transfer(dto);

        assertEquals(
                new BigDecimal("800"),
                account.getAccount_balance()
        );

        assertEquals(
                new BigDecimal("400"),
                account2.getAccount_balance()
        );

        verify(accountRepository).save(account);

        verify(accountRepository).save(account2);

        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void shouldThrowExceptionWhenBalanceIsInsufficient() {
        Account account = new Account();
        account.setAccount_balance(
                new BigDecimal("100")
        );

        SaqueRequestDTO dto =
                new SaqueRequestDTO(
                        1L,
                        new BigDecimal("200")
                );

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));


        InsufficientBalanceException exception =
                assertThrows(
                InsufficientBalanceException.class,
                () -> transactionServices.saque(dto)
        );

        assertEquals(
                "Saldo insuficiente!",
                exception.getMessage()
        );

        verify(accountRepository, never()).save(account);

        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    void shouldThrowExceptionWhenAccountDoesNotExist() {

        DepositRequestDTO dto =
                new DepositRequestDTO(
                        1L,
                        new BigDecimal("200")
                );

        when(accountRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                AccountNotFoundException.class,
                () -> transactionServices.deposit(dto)
        );

        verify(transactionRepository, never()).save(any(Transaction.class));
    }
}
