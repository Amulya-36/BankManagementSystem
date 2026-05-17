package com.bank.main;

import com.bank.service.*;
import com.bank.model.Customer;

import java.util.Scanner;
import com.bank.model.User;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
AuthenticationService Authenticate=new AuthenticationServiceImpl();
        CustomerService cs = new CustomerServiceImpl();
        AccountService as=new AccountServiceImpl();
        TransactionService  ts=new TransactionServiceImpl();

        System.out.println("enter username: ");
        String username=sc.nextLine();

        System.out.println("enter password: ");
        String password=sc.nextLine();
        User user=Authenticate.login(username,password);
        if(user==null)
        {
            System.out.println("Invalid Login");
            return;
        }
        System.out.println("Welcome " + user.getUsername() + " (" + user.getRole() + ")");
        if(user.getRole().equals("employee"))
        {
            while (true) {

                System.out.println("1. Add Customer");
                System.out.println("2. Create Account");
                System.out.println("3.View Customers");
                System.out.println("4.View Accounts");
                System.out.println("5.View Transactions");
                System.out.println("6. Exit");
                int ch=sc.nextInt();
                switch(ch)
                {
                    case 1:
                        Customer c = new Customer();
                        System.out.println("Enter ID");
                        c.setCustomerId(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Enter name");
                        c.setName(sc.nextLine());
                        System.out.println("Enter phone");
                        c.setPhone(sc.nextLine());
                        System.out.println("Enter email");
                        c.setEmail(sc.nextLine());
                        System.out.println("Enter address");
//                        sc.nextLine();
                        c.setAddress(sc.nextLine());
                       c.setUserId(user.getUserId());

                        cs.createCustomer(c);
                        break;
                    case 2:
                        System.out.println("Enter Customer ID:");
                        int customerId = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Enter Account Type:");
                        String type = sc.nextLine();

                        as.createAccount(customerId, type);  // ✅ simple values
                        break;
                    case 3:

                        cs.viewCustomers();
                        break;
                    case 4:

                        as.viewAccounts();
                        break;
                    case 5:
                        ts.viewTransactions();
                        break;
                    case 6:
                        System.out.println("Thank you!");
                        System.exit(0);

                }

            }
        }
        if(user.getRole().equals("customer")) {

            while (true) {

//                if(user.getRole().equals("customer")) {
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Check Balance");
                    System.out.println("4. Mini Statement");
                    System.out.println("5. Exit");

                    int choice = sc.nextInt();

                    switch (choice) {

                        case 1:
                            System.out.println("Enter Account Number:");
                            long accNo = sc.nextLong();

                            System.out.println("Enter Amount:");
                            double amount = sc.nextDouble();

                            ts.deposit(accNo, amount);   // ✅ simple values
                            break;
                        case 2:
                            System.out.println("Enter Account Number:");
                            long accNoW = sc.nextLong();

                            System.out.println("Enter Amount:");
                            double amt = sc.nextDouble();

                            ts.withdraw(accNoW, amt);
                            break;
                        case 3:
                            System.out.println("check balance");
                            ts.checkBalance(sc.nextLong());
                            break;
                        case 4:
                            System.out.println("Enter Account Number");
                            ts.miniStatement(sc.nextLong());
                            break;
                        case 5:
                            System.out.println("Thank you!");
                            System.exit(0);

                    }
                }
            }
            }






        }

