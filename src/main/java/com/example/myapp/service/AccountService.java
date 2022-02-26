package com.example.myapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.dto.AccountDto;
import com.example.myapp.entity.Account;
import com.example.myapp.exception.AccountNotFoundException;
import com.example.myapp.repository.AccountRepository;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account create(AccountDto.SignUpReq dto) {
		if(accountRepository.existsById(dto.getEmail()))
			throw new DuplicateKeyException(dto.getEmail());
	    return accountRepository.save(dto.toEntity());
	}

	@Transactional(readOnly = true)
	public Account findById(String email) {
//		Example<Account> example = Example.of(Account.builder().email(email).build());
//	    final Optional<Account> account = accountRepository.findOne(example).get();
	    final Account account = accountRepository.getById(email);
	    if (account == null)
	        throw new AccountNotFoundException(email);
	    return account;
	}
	
	public Account updateAccount(String email, AccountDto.UpdateAccountReq dto) {
	    final Account account = findById(email);
	    account.updateMyAccount(dto);
	    return account;
	}
}
