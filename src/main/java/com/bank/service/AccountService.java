package com.bank.service;

public interface AccountService {
    void createAccount(int customerId, String accountType);
    void viewAccounts();
}
