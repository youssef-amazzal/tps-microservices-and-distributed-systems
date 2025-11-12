package org.glsid.bankaccountmicroservice.repositories;

import org.glsid.bankaccountmicroservice.entities.BankAccount;
import org.glsid.bankaccountmicroservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByType(AccountType type);
}
