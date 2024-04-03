package com.nagarro.accountmanagement;

import com.nagarro.accountmanagement.dto.AccountDto;
import com.nagarro.accountmanagement.entity.Account;
import com.nagarro.accountmanagement.exceptions.AccountNotFoundException;
import com.nagarro.accountmanagement.mapper.AccountMapper;
import com.nagarro.accountmanagement.repository.AccountRepository;
import com.nagarro.accountmanagement.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void testAddAccount() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setCustomerId(100L);
        account.setBalance(1000.0);

        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(1L);
        accountDto.setCustomerId(100L);
        accountDto.setBalance(1000.0);

        when(accountMapper.accountToAccountDto(any())).thenReturn(accountDto);
        when(accountRepository.save(any())).thenReturn(account);

        AccountDto result = accountService.addAccount(account);

        assertEquals(accountDto.getAccountId(), result.getAccountId());
        assertEquals(accountDto.getCustomerId(), result.getCustomerId());
        assertEquals(accountDto.getBalance(), result.getBalance());
    }

    @Test
    void testGetAccountById() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setCustomerId(100L);
        account.setBalance(1000.0);

        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(1L);
        accountDto.setCustomerId(100L);
        accountDto.setBalance(1000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountMapper.accountToAccountDto(account)).thenReturn(accountDto);

        AccountDto result = accountService.getAccountById(1L);

        assertEquals(accountDto.getAccountId(), result.getAccountId());
        assertEquals(accountDto.getCustomerId(), result.getCustomerId());
        assertEquals(accountDto.getBalance(), result.getBalance());
    }

    @Test
    void testDeleteAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account()));

        accountService.deleteAccount(1L);

        verify(accountRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAccount_NotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.deleteAccount(1L));
    }

}
