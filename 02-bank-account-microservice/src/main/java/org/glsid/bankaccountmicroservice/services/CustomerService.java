package org.glsid.bankaccountmicroservice.services;

import org.glsid.bankaccountmicroservice.dto.CustomerRequestDTO;
import org.glsid.bankaccountmicroservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomerById(Long id);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO);
    void deleteCustomer(Long id);
}

