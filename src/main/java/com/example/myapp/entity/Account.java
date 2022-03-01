package com.example.myapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.myapp.dto.AccountDto;
import com.example.myapp.model.Address;
import com.example.myapp.model.Name;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Embedded
    private Name name;
    
    @NotBlank
    private String password;

    @Embedded
    private Address address;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date latest_login_at;

    @Builder
    public Account(String email, Name name, String password, Address address, String zip) {
    	this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public void updateAccount(AccountDto.UpdateAccountReq dto) {
        this.address = dto.getAddress();
    }
}
