package org.glsid.bankaccountmicroservice;

import org.glsid.bankaccountmicroservice.entities.BankAccount;
import org.glsid.bankaccountmicroservice.enums.AccountType;
import org.glsid.bankaccountmicroservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                BankAccount bankAccount = BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .balance(Math.random() * 80000)
                    .currency("MAD")
                    .createAt(new Date())
                    .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                    .build();
                bankAccountRepository.save(bankAccount);
            }

        };
    }

}