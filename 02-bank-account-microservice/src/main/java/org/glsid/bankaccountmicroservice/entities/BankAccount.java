package org.glsid.bankaccountmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.glsid.bankaccountmicroservice.enums.AccountType;

@Entity
@Data @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String accountId;
    private Double balance;
    private Long createAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
