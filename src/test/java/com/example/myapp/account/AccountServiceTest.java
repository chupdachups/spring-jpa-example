package com.example.myapp.account;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import com.example.myapp.dto.AccountDto;
import com.example.myapp.entity.Account;
import com.example.myapp.exception.AccountNotFoundException;
import com.example.myapp.repository.AccountRepository;
import com.example.myapp.service.AccountService;

@SpringBootTest
public class AccountServiceTest {
	
	@InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;


    @Test
    public void create_회원가입_성공() {
        //given
        final AccountDto.SignUpReq dto = buildSignUpReq();
        given(accountRepository.save(any(Account.class))).willReturn(dto.toEntity());

        //when
        final Account account = accountService.create(dto);

        //then
        assertThatEqual(dto, account);
    }
    
    @Test
    public void create_회원가입_실패_DuplicateKeyException() {
        //given
        final AccountDto.SignUpReq dto = buildSignUpReqFail();
        given(accountRepository.existsById(anyString())).willReturn(true);

        assertThrows(DuplicateKeyException.class, () -> {
        	accountService.create(dto);
        });
    }
    
    @Test
    public void findById_존재하는경우_계정리턴() {
        //given
        final AccountDto.SignUpReq dto = buildSignUpReq();
        given(accountRepository.getById(anyString())).willReturn(dto.toEntity());

        //when
        final Account account = accountService.findById("tiger@korea.com");

        //then
        assertThatEqual(dto, account);
    }
    
    @Test
    public void findById_존재하지않은경우_AccountNotFoundException() {
        //given
        given(accountRepository.getById(anyString())).willReturn(null);

        assertThrows(AccountNotFoundException.class, () -> {
        	 accountService.findById(anyString());
        });
    }
    
    @Test
    public void updateAccount() {
        //given
        final AccountDto.SignUpReq signUpReq = buildSignUpReq();
        final AccountDto.UpdateAccountReq dto = buildUpdateAccountReq();
        given(accountRepository.getById(anyString())).willReturn(signUpReq.toEntity());

        //when
        final Account account = accountService.updateAccount("tiger@korea.com", dto);

        //then
        assertThat(dto.getAddress1(), is(account.getAddress1()));
        assertThat(dto.getZip(), is(account.getZip()));
    }
    
    private AccountDto.SignUpReq buildSignUpReq() {
        return AccountDto.SignUpReq.builder()
                .address1("프랑스")
                .zip("12345")
                .email("dictionary@france.com")
                .firstName("나폴레옹")
                .lastName("napol")
                .password("short")
                .build();
    }
    
    private AccountDto.SignUpReq buildSignUpReqFail() {
        return AccountDto.SignUpReq.builder()
        		.firstName("호랑이")
    			.lastName("tiger")
    			.email("tiger@korea.com")
    			.password("lion")
    			.address1("주소1")
    			.zip("12345")
    			.build();
    }
    
    private AccountDto.UpdateAccountReq buildUpdateAccountReq() {
        return AccountDto.UpdateAccountReq.builder()
                .address1("korea")
                .zip("98765")
                .build();
    }
    
    private void assertThatEqual(AccountDto.SignUpReq signUpReq, Account account) {
        assertThat(signUpReq.getAddress1(), is(account.getAddress1()));
        assertThat(signUpReq.getZip(), is(account.getZip()));
        assertThat(signUpReq.getEmail(), is(account.getEmail()));
        assertThat(signUpReq.getFirstName(), is(account.getFirstName()));
        assertThat(signUpReq.getLastName(), is(account.getLastName()));
        assertThat(signUpReq.getPassword(), is(account.getPassword()));
    }
}