package org.glsid.bankaccountmicroservice.entities;

import org.glsid.bankaccountmicroservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;


@Projection(types = BankAccount.class, name = "P1")
public interface AccountProjection {
    public String getAccountId();
    public AccountType getType();
    public Double getBalance();
}
