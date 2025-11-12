package org.glsid.bankaccountmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.glsid.bankaccountmicroservice.enums.AccountType;
import java.util.Date;

@Entity
@Data @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String accountId;
    private Double balance;
    private Date createAt;
    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType type;
}
