package com.example.myapp.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.myapp.entity.Account;
import com.example.myapp.model.Address;
import com.example.myapp.model.Name;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

public class AccountDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq {
    	
    	@Email
        private String email;
    	
    	@Valid
    	private Name name;
    	
    	@NotBlank
        private String password;
    	
    	@Valid
    	private Address address;

        @Builder
        public SignUpReq(String email, Name name, String password, Address address, String zip) {
            this.email = email;
            this.name = name;
            this.password = password;
            this.address = address;
        }

        public Account toEntity() {
            return Account.builder()
                    .email(this.email)
                    .name(this.name)
                    .password(this.password)
                    .address(address)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateAccountReq {
    	
        @Valid
    	private Address address;

        @Builder
        public UpdateAccountReq(Address address, String zip) {
            this.address = address;
        }
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginReq {
    	
    	@Email
    	@NonNull
        private String email;
    	
    	@NotBlank
        private String password;

        @Builder
        public LoginReq(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    @Getter
    public static class Res {
        private String email;
        private Name name;
        private Address address;

        public Res(Account account) {
            this.email = account.getEmail();
            this.name = account.getName();
            this.address = account.getAddress();
        }
    }
}
