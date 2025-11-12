package org.glsid.bankaccountmicroservice.mappers;

import lombok.RequiredArgsConstructor;
import org.glsid.bankaccountmicroservice.dto.CustomerRequestDTO;
import org.glsid.bankaccountmicroservice.dto.CustomerResponseDTO;
import org.glsid.bankaccountmicroservice.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final AccountMapper accountMapper;

    public CustomerResponseDTO fromCustomer(Customer customer) {
        if (customer == null) return null;

        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .accounts(customer.getAccounts() != null ?
                    accountMapper.fromBankAccountList(customer.getAccounts()) : null)
                .build();
    }

    public CustomerResponseDTO fromCustomerWithoutAccounts(Customer customer) {
        if (customer == null) return null;

        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }

    public Customer toCustomer(CustomerRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        return Customer.builder()
                .name(requestDTO.getName())
                .build();
    }

    public void updateCustomerFromDTO(Customer customer, CustomerRequestDTO requestDTO) {
        if (customer == null || requestDTO == null) return;

        if (requestDTO.getName() != null)
            customer.setName(requestDTO.getName());
    }

    public List<CustomerResponseDTO> fromCustomerList(List<Customer> customers) {
        if (customers == null) return null;

        return customers.stream()
                .map(this::fromCustomer)
                .collect(Collectors.toList());
    }
}

