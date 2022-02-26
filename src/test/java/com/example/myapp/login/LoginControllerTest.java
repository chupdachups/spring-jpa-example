package com.example.myapp.login;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.myapp.controller.LoginController;
import com.example.myapp.dto.AccountDto;
import com.example.myapp.entity.Account;
import com.example.myapp.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class LoginControllerTest {
	
	private Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);

	@InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }
    
    @Test
    public void login() throws Exception {
        //given
        final AccountDto.LoginReq dto = buildLoginReq();
        Account account = buildAccount();
        given(loginService.login(any(AccountDto.LoginReq.class))).willReturn(account);

        //when
        final ResultActions resultActions = requestLogin(dto);
        
        logger.info(resultActions.toString());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address1", is(account.getAddress1())))
                .andExpect(jsonPath("$.zip", is(account.getZip())))
                .andExpect(jsonPath("$.email", is(account.getEmail())))
                .andExpect(jsonPath("$.firstName", is(account.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(account.getLastName())));
    }

    private ResultActions requestLogin(AccountDto.LoginReq dto) throws Exception {
        return mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());
    }
    
    private AccountDto.LoginReq buildLoginReq() {
        return AccountDto.LoginReq.builder()
        		.email("tiger@korea.com")
        		.password("lion")
        		.build();
   
    }
    
    private Account buildAccount() {
    	return Account.builder()
    			.firstName("호랑이")
    			.lastName("tiger")
    			.email("tiger@korea.com")
    			.address1("주소1")
    			.zip("12345")
    			.build();
    }

}
