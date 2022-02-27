package com.example.myapp.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.example.myapp.entity.Account;
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
    	
    	@NotEmpty
        private String address1;
    	@NotEmpty
        private String zip;

        @Builder
        public SignUpReq(String email, Name name, String password, String address1, String zip) {
            this.email = email;
            this.name = name;
            this.password = password;
            this.address1 = address1;
            this.zip = zip;
        }

        public Account toEntity() {
            return Account.builder()
                    .email(this.email)
                    .name(this.name)
                    .password(this.password)
                    .address1(this.address1)
                    .zip(this.zip)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateAccountReq {
        private String address1;
        private String zip;

        @Builder
        public UpdateAccountReq(String address1, String zip) {
            this.address1 = address1;
            this.zip = zip;
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
        private String address1;
        private String zip;

        public Res(Account account) {
            this.email = account.getEmail();
            this.name = account.getName();
            this.address1 = account.getAddress1();
            this.zip = account.getZip();
        }
    }
}
