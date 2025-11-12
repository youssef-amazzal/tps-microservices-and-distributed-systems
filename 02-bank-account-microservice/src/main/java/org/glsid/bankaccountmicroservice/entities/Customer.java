package org.glsid.bankaccountmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<BankAccount> accounts;
}

