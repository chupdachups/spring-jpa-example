package com.example.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myapp.entity.Account;

public interface LoginRepository  extends JpaRepository<Account, String> {

}
