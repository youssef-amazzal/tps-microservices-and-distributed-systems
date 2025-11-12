package org.glsid.bankaccountmicroservice.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.glsid.bankaccountmicroservice.dto.BankAccountRequestDTO;
import org.glsid.bankaccountmicroservice.dto.BankAccountResponseDTO;
import org.glsid.bankaccountmicroservice.entities.BankAccount;
import org.glsid.bankaccountmicroservice.mappers.AccountMapper;
import org.glsid.bankaccountmicroservice.repositories.BankAccountRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = accountMapper.toBankAccount(bankAccountRequestDTO);
        bankAccount.setAccountId(UUID.randomUUID().toString());
        bankAccount.setCreateAt(new Date());

        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        return accountMapper.fromBankAccount(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public BankAccountResponseDTO getAccountById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        return accountMapper.fromBankAccount(bankAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankAccountResponseDTO> getAllAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return accountMapper.fromBankAccountList(accounts);
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));

        accountMapper.updateBankAccountFromDTO(bankAccount, bankAccountRequestDTO);

        BankAccount updatedAccount = bankAccountRepository.save(bankAccount);
        return accountMapper.fromBankAccount(updatedAccount);
    }

    @Override
    public void deleteAccount(String id) {
        if (!bankAccountRepository.existsById(id)) {
            throw new RuntimeException(String.format("Account %s not found", id));
        }
        bankAccountRepository.deleteById(id);
    }
}
