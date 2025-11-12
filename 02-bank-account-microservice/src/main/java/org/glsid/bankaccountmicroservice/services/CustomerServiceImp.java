package org.glsid.bankaccountmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.glsid.bankaccountmicroservice.dto.CustomerRequestDTO;
import org.glsid.bankaccountmicroservice.dto.CustomerResponseDTO;
import org.glsid.bankaccountmicroservice.entities.Customer;
import org.glsid.bankaccountmicroservice.mappers.CustomerMapper;
import org.glsid.bankaccountmicroservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.toCustomer(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.fromCustomerWithoutAccounts(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findByIdWithAccounts(id)
                .orElseThrow(() -> new RuntimeException(String.format("Customer %s not found", id)));
        return customerMapper.fromCustomer(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAllWithAccounts();
        return customerMapper.fromCustomerList(customers);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Customer %s not found", id)));

        customerMapper.updateCustomerFromDTO(customer, customerRequestDTO);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.fromCustomerWithoutAccounts(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException(String.format("Customer %s not found", id));
        }
        customerRepository.deleteById(id);
    }
}

