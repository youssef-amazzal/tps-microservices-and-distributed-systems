package org.glsid.bankaccountmicroservice.web;

import org.glsid.bankaccountmicroservice.dto.BankAccountRequestDTO;
import org.glsid.bankaccountmicroservice.dto.BankAccountResponseDTO;
import org.glsid.bankaccountmicroservice.dto.CustomerRequestDTO;
import org.glsid.bankaccountmicroservice.dto.CustomerResponseDTO;
import org.glsid.bankaccountmicroservice.services.AccountService;
import org.glsid.bankaccountmicroservice.services.CustomerService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccountGraphqlController {
    private final AccountService accountService;
    private final CustomerService customerService;


    @QueryMapping
    public List<BankAccountResponseDTO> accounts() {
        return accountService.getAllAccounts();
    }


    @QueryMapping
    public BankAccountResponseDTO accountById(@Argument String id) {
        return accountService.getAccountById(id);
    }


    @MutationMapping
    public BankAccountResponseDTO createAccount(@Argument BankAccountRequestDTO request) {
        return accountService.addAccount(request);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO request) {
        return accountService.updateAccount(id, request);
    }

    @MutationMapping
    public boolean deleteAccount(@Argument String id) {
        accountService.deleteAccount(id);
        return true;
    }

    @QueryMapping
    public List<CustomerResponseDTO> customers() {
        return customerService.getAllCustomers();
    }

    @QueryMapping
    public CustomerResponseDTO customerById(@Argument Long id) {
        return customerService.getCustomerById(id);
    }

    @MutationMapping
    public CustomerResponseDTO createCustomer(@Argument CustomerRequestDTO request) {
        return customerService.addCustomer(request);
    }

    @MutationMapping
    public CustomerResponseDTO updateCustomer(@Argument Long id, @Argument CustomerRequestDTO request) {
        return customerService.updateCustomer(id, request);
    }

    @MutationMapping
    public boolean deleteCustomer(@Argument Long id) {
        customerService.deleteCustomer(id);
        return true;
    }
}
