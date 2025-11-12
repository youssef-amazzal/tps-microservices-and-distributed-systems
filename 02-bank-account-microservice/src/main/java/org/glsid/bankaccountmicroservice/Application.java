package org.glsid.bankaccountmicroservice;

import org.glsid.bankaccountmicroservice.entities.BankAccount;
import org.glsid.bankaccountmicroservice.entities.Customer;
import org.glsid.bankaccountmicroservice.enums.AccountType;
import org.glsid.bankaccountmicroservice.repositories.BankAccountRepository;
import org.glsid.bankaccountmicroservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository,
                                       CustomerRepository customerRepository) {
        return args -> {
            Customer customer1 = Customer.builder()
                    .name("Ahmed")
                    .build();
            customerRepository.save(customer1);

            Customer customer2 = Customer.builder()
                    .name("Anass")
                    .build();
            customerRepository.save(customer2);

            Customer customer3 = Customer.builder()
                    .name("Youssef")
                    .build();
            customerRepository.save(customer3);

            for (int i = 0; i < 10; i++) {
                Customer customer;
                if (i < 3) customer = customer1;
                else if (i < 6) customer = customer2;
                else customer = customer3;

                BankAccount bankAccount = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("MAD")
                        .balance(Math.random() * 80000)
                        .createAt(System.currentTimeMillis())
                        .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .customer(customer)
                        .build();
                accountRepository.save(bankAccount);
            }
        };
    }
}