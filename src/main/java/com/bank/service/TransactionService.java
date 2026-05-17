package com.bank.service;

public interface TransactionService {
    void deposit(long accountNumber,double amount);
    void withdraw(long accountNumber,double amount);
    void checkBalance(long accountNumber);
    void miniStatement(long accountNumber);
    void viewTransactions();
}
