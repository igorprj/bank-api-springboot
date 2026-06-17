package com.api.API_Bancaria.services;

import com.api.API_Bancaria.Repositories.AccountRepository;
import com.api.API_Bancaria.model.Account;
import com.api.API_Bancaria.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository
                .findByAccountcpf(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        return new CustomUserDetails(account);
    }
}
