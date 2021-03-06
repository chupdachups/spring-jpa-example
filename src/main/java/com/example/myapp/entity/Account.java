package com.example.myapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.myapp.dto.AccountDto;
import com.example.myapp.model.Name;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

//    @NonNull
//    private String firstName;
//    @NonNull
//    private String lastName;
    @Valid
    private Name name;
    
    @NonNull
    private String password;

    @NonNull
    private String address1;

    @NonNull
    private String zip;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date latest_login_at;

    @Builder
    public Account(String email, Name name, String password, String address1, String zip) {
    	this.email = email;
        this.name = name;
        this.password = password;
        this.address1 = address1;
        this.zip = zip;
    }

    public void updateAccount(AccountDto.UpdateAccountReq dto) {
        this.address1 = dto.getAddress1();
        this.zip = dto.getZip();
    }
}
