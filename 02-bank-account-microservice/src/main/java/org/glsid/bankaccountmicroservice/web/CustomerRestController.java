package org.glsid.bankaccountmicroservice.web;

import lombok.RequiredArgsConstructor;
import org.glsid.bankaccountmicroservice.dto.CustomerRequestDTO;
import org.glsid.bankaccountmicroservice.dto.CustomerResponseDTO;
import org.glsid.bankaccountmicroservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CustomerResponseDTO createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.addCustomer(customerRequestDTO);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.updateCustomer(id, customerRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}

