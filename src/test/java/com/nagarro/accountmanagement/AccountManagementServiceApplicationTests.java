package com.nagarro.accountmanagement;

import com.nagarro.accountmanagement.controller.AccountController;
import com.nagarro.accountmanagement.dto.AccountDto;
import com.nagarro.accountmanagement.dto.MoneyRequestDto;
import com.nagarro.accountmanagement.entity.Account;
import com.nagarro.accountmanagement.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountManagementServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	@InjectMocks
	private AccountController accountController;

	@Test
	void testContextLoads() {
	}

	// POST Endpoint Test Cases
	@Test
	public void testAddAccount() throws Exception {
		// Mock data
		Account account = new Account();
		account.setAccountId(1L);
		when(accountService.addAccount(any(Account.class))).thenReturn(new AccountDto(account));

		String requestPayload = "{\"customerId\": 123, \"name\": \"Shivam Mittal\", \"email\": \"shivam.mittal@nagarro.com\", \"balance\": 10076.0}";

		MvcResult result = mockMvc.perform(post("/accounts")
						.content(requestPayload)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

	}
}
