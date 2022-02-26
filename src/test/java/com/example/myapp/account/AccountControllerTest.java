package com.example.myapp.account;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.myapp.controller.AccountController;
import com.example.myapp.dto.AccountDto;
import com.example.myapp.entity.Account;
import com.example.myapp.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class AccountControllerTest {

	@InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void signUp() throws Exception {
        //given
        final AccountDto.SignUpReq dto = buildSignUpReq();
        given(accountService.create(any())).willReturn(dto.toEntity());

        //when
        final ResultActions resultActions = requestSignUp(dto);

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.address1", is(dto.getAddress1())))
                .andExpect(jsonPath("$.zip", is(dto.getZip())))
                .andExpect(jsonPath("$.email", is(dto.getEmail())))
                .andExpect(jsonPath("$.firstName", is(dto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(dto.getLastName())));
    }
    
    @Test
    public void getAccount() throws Exception {
        //given
        final AccountDto.SignUpReq dto = buildSignUpReq();
        given(accountService.findById(anyString())).willReturn(dto.toEntity());

        //when
        final ResultActions resultActions = requestGetAccount();

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address1", is(dto.getAddress1())))
                .andExpect(jsonPath("$.zip", is(dto.getZip())))
                .andExpect(jsonPath("$.email", is(dto.getEmail())))
                .andExpect(jsonPath("$.firstName", is(dto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(dto.getLastName())));
    }
    
    @Test
    public void updateAccount() throws Exception {
        //given
        final AccountDto.UpdateAccountReq dto = buildUpdateAccountReq();
        final Account account = Account.builder()
                .address1(dto.getAddress1())
                .zip(dto.getZip())
                .build();

        given(accountService.updateAccount(anyString(), any(AccountDto.UpdateAccountReq.class))).willReturn(account);

        //when
        final ResultActions resultActions = requestUpdateAccount(dto);

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address1", is(dto.getAddress1())))
                .andExpect(jsonPath("$.zip", is(dto.getZip())));

    }
    
    private ResultActions requestSignUp(AccountDto.SignUpReq dto) throws Exception {
        return mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());
    }
    
    private ResultActions requestGetAccount() throws Exception {
        return mockMvc.perform(get("/accounts/tiger@korea.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    
    private ResultActions requestUpdateAccount(AccountDto.UpdateAccountReq dto) throws Exception {
        return mockMvc.perform(put("/accounts/tiger@korea.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());
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
    
    private AccountDto.UpdateAccountReq buildUpdateAccountReq() {
        return AccountDto.UpdateAccountReq.builder()
                .address1("코리아")
                .zip("98765")
                .build();
    }


}
