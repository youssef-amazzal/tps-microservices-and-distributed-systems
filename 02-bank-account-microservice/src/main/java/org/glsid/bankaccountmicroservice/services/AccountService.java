package org.glsid.bankaccountmicroservice.services;

import org.glsid.bankaccountmicroservice.dto.BankAccountRequestDTO;
import org.glsid.bankaccountmicroservice.dto.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);
    BankAccountResponseDTO getAccountById(String id);
    List<BankAccountResponseDTO> getAllAccounts();
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO);
    void deleteAccount(String id);
}
