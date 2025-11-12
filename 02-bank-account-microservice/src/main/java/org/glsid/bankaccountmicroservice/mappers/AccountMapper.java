package org.glsid.bankaccountmicroservice.mappers;

import org.glsid.bankaccountmicroservice.dto.BankAccountRequestDTO;
import org.glsid.bankaccountmicroservice.dto.BankAccountResponseDTO;
import org.glsid.bankaccountmicroservice.entities.BankAccount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        if (bankAccount == null) return null;

        return BankAccountResponseDTO.builder()
                .accountId(bankAccount.getAccountId())
                .balance(bankAccount.getBalance())
                .currency(bankAccount.getCurrency())
                .type(bankAccount.getType())
                .createAt(bankAccount.getCreateAt())
                .customerId(bankAccount.getCustomer() != null ? bankAccount.getCustomer().getId() : null)
                .customerName(bankAccount.getCustomer() != null ? bankAccount.getCustomer().getName() : null)
                .build();
    }

    public BankAccount toBankAccount(BankAccountRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        return BankAccount.builder()
                .balance(requestDTO.getBalance())
                .currency(requestDTO.getCurrency())
                .type(requestDTO.getType())
                .build();
    }

    public void updateBankAccountFromDTO(BankAccount bankAccount, BankAccountRequestDTO requestDTO) {
        if (bankAccount == null || requestDTO == null) return;

        if (requestDTO.getBalance() != null)
            bankAccount.setBalance(requestDTO.getBalance());

        if (requestDTO.getCurrency() != null)
            bankAccount.setCurrency(requestDTO.getCurrency());

        if (requestDTO.getType() != null)
            bankAccount.setType(requestDTO.getType());
    }

    public List<BankAccountResponseDTO> fromBankAccountList(List<BankAccount> bankAccounts) {
        if (bankAccounts == null) return null;

        return bankAccounts.stream()
                .map(this::fromBankAccount)
                .collect(Collectors.toList());
    }
}
