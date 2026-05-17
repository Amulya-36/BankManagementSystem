package com.bank.service;

import com.bank.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class AccountServiceImpl implements AccountService{
    @Override
    public void createAccount(int customerId, String accountType) {
        try(Connection con= Util.getConnection()) {

            Random rand = new Random();
            long accNo = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
            String sql = "INSERT INTO accounts(account_number, account_type, customer_id, balance) VALUES (?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, accNo);
            ps.setString(2, accountType);
            ps.setInt(3, customerId);
            ps.setDouble(4, 0.0);

            ps.executeUpdate();
            System.out.println("Account Created: " + accNo);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
//    private int getAccountId(Connection con, long accNo) throws Exception {
//        String sql = "SELECT account_id FROM accounts WHERE account_number=?";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setLong(1, accNo);
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next()) return rs.getInt(1);
//        throw new RuntimeException("Account not found");
//    }

    @Override
    public void viewAccounts() {
        try (Connection con = Util.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from accounts");
            System.out.println("account_id  |  account_number |  account_type  | customer_id  |  balance");
            while (rs.next()) {
                int account_id = rs.getInt("account_id");
                Long account_number  = rs.getLong("account_number");
                String account_type  = rs.getString("account_type");
                int customer_id = rs.getInt("customer_id");
                Double balance = rs.getDouble("balance");
                System.out.println(account_id + " " + account_number + " " + account_type  + " " + customer_id + " "+balance);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
