package org.glsid.bankaccountmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.glsid.bankaccountmicroservice.enums.AccountType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountResponseDTO {
    private String accountId;
    private Double balance;
    private String currency;
    private AccountType type;
    private Long createAt;
    private Long customerId;
    private String customerName;
}