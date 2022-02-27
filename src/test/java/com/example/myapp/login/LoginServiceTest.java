package com.example.myapp.login;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.myapp.dto.AccountDto;
import com.example.myapp.entity.Account;
import com.example.myapp.exception.AccountNotFoundException;
import com.example.myapp.exception.WrongPasswordException;
import com.example.myapp.model.Name;
import com.example.myapp.repository.LoginRepository;
import com.example.myapp.service.LoginService;

@SpringBootTest
public class LoginServiceTest {

	@InjectMocks
	private LoginService loginService;
	
	@Mock
	private LoginRepository loginRepository;
	
	@Test
	@DisplayName("로그인 성공")
	public void login_success() {
		//given
		final AccountDto.SignUpReq dto = buildSignUpReq();
		given(loginRepository.existsById(anyString())).willReturn(true);
		given(loginRepository.getById(anyString())).willReturn(dto.toEntity());
		
		//when
		final Account account = loginService.login(buildLoginReq());
		
		//then
//		verify(loginRepository, atLeastOnce()).getById(anyString());
		assertThatEqual(dto, account);
		
	}
	
	@Test
	@DisplayName("로그인 실패 - 계정이 없는 경우")
	public void login_실패_AccountNotFoundException() {
		//given
		given(loginRepository.existsById(anyString())).willReturn(false);
		
		assertThrows(AccountNotFoundException.class,() -> {
			loginService.login(buildLoginReq());
		});
		
	}
	
	@Test
	@DisplayName("로그인 실패 - 비밀번호 틀린 경우 ")
	public void login_실패_WrongPasswordException() {
		//given
		final AccountDto.SignUpReq dto = buildSignUpReq();
		given(loginRepository.existsById(anyString())).willReturn(true);
		given(loginRepository.getById(anyString())).willReturn(dto.toEntity());
		
		assertThrows(WrongPasswordException.class,() -> {
			loginService.login(buildLoginReqWrongPw());
		});
		
	}

    private AccountDto.SignUpReq buildSignUpReq() {
        return AccountDto.SignUpReq.builder()
        		.name(Name.builder().first("호랑이").last("tiger").build())
    			.email("tiger@korea.com")
    			.password("lion")
    			.address1("주소1")
    			.zip("12345")
    			.build();
   
    }
    
    private AccountDto.LoginReq buildLoginReq() {
        return AccountDto.LoginReq.builder()
        		.email("tiger@korea.com")
        		.password("lion")
        		.build();
   
    }
    
    private AccountDto.LoginReq buildLoginReqWrongPw() {
        return AccountDto.LoginReq.builder()
        		.email("tiger@korea.com")
        		.password("rabbit")
        		.build();
   
    }
    
    private void assertThatEqual(AccountDto.SignUpReq signUpReq, Account account) {
        assertThat(signUpReq.getEmail(), is(account.getEmail()));
        assertThat(signUpReq.getPassword(), is(account.getPassword()));
    }
}
