package com.bank.service;

import com.bank.model.User;

public interface AuthenticationService {
    User login(String username, String password);
}