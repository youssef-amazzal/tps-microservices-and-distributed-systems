package org.glsid.bankaccountmicroservice.repositories;

import org.glsid.bankaccountmicroservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.accounts")
    List<Customer> findAllWithAccounts();

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.accounts WHERE c.id = :id")
    Optional<Customer> findByIdWithAccounts(Long id);
}

