package com.nagarro.accountmanagement.dto;

import com.nagarro.accountmanagement.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long accountId;
    private Long customerId;
    private String name;
    private String email;
    private double balance;

    public AccountDto(Account account) {
    }
}
