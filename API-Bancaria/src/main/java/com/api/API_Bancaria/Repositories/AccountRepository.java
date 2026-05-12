package com.api.API_Bancaria.Repositories;

import com.api.API_Bancaria.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
