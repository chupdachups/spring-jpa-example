package com.example.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.dto.AccountDto;
import com.example.myapp.entity.Account;
import com.example.myapp.exception.AccountNotFoundException;
import com.example.myapp.exception.WrongPasswordException;
import com.example.myapp.repository.LoginRepository;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Transactional(readOnly = true)
	public Account login(AccountDto.LoginReq dto) {
//		Example<Account> example = Example.of(Account.builder().email(dto.getEmail()).build());
//	    final Account account = accountRepository.findOne(example).get();
		final Account account = loginRepository.getById(dto.getEmail());
	    if (account == null)
	        throw new AccountNotFoundException(dto.getEmail());
	    
	    if (!(dto.getPassword().equals(account.getPassword())))
	    	throw new WrongPasswordException(dto.getEmail());
	    
	    return account;
	}

}
