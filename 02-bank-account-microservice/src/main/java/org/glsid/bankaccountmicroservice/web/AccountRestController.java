package org.glsid.bankaccountmicroservice.web;

import lombok.RequiredArgsConstructor;
import org.glsid.bankaccountmicroservice.dto.BankAccountRequestDTO;
import org.glsid.bankaccountmicroservice.dto.BankAccountResponseDTO;
import org.glsid.bankaccountmicroservice.services.AccountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping
    public List<BankAccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public BankAccountResponseDTO getAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public BankAccountResponseDTO createAccount(@RequestBody BankAccountRequestDTO bankAccountRequestDTO) {
        return accountService.addAccount(bankAccountRequestDTO);
    }

    @PutMapping("/{id}")
    public BankAccountResponseDTO updateAccount(
            @PathVariable String id,
            @RequestBody BankAccountRequestDTO bankAccountRequestDTO) {
        return accountService.updateAccount(id, bankAccountRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
    }
}
