package org.glsid.bankaccountmicroservice.web;

import org.glsid.bankaccountmicroservice.entities.BankAccount;
import org.glsid.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AccountRestController {
    private final BankAccountRepository accountRepository;


    public AccountRestController(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        return accountRepository.findAll();
    }
    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        return accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/accounts")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount) {
        if(bankAccount.getAccountId() == null ) bankAccount.setAccountId(java.util.UUID.randomUUID().toString());
        return accountRepository.save(bankAccount);
    }
    @PutMapping("/accounts/{id}")
    public BankAccount updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
        if(bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getType() != null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        bankAccount= accountRepository.save(account);
        return bankAccount;
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteBankAccount(@PathVariable String id) {
        accountRepository.deleteById(id);
    }


}
