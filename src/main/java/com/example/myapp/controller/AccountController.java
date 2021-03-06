package com.example.myapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.dto.AccountDto;
import com.example.myapp.service.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {
	
	@Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountDto.Res signUp(@RequestBody @Valid final AccountDto.SignUpReq dto) {
        return new AccountDto.Res(accountService.create(dto));
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDto.Res getAccount(@PathVariable final String email) {
        return new AccountDto.Res(accountService.findById(email));
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDto.Res updateMyAccount(@PathVariable final String email, @RequestBody final AccountDto.UpdateAccountReq dto) {
        return new AccountDto.Res(accountService.updateAccount(email, dto));
    }

}
