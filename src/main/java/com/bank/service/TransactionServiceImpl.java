package com.bank.service;

import com.bank.dao.AccountDAO;
import com.bank.util.Util;

import java.sql.*;

public class TransactionServiceImpl  implements TransactionService{
    @Override
    public void deposit(long accountNumber, double amount) {
        try(Connection con= Util.getConnection())
        {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE accounts SET balance=balance+? where account_number=? ");
            ps.setDouble(1,amount);
            ps.setLong(2,accountNumber);
            ps.executeUpdate();
            int accountId = AccountDAO.getAccountId(con, accountNumber);
            PreparedStatement p=con.prepareStatement("insert into transactions(account_id,transaction_type,amount) values(?,?,?)");
            p.setLong(1,accountId);
            p.setString(2,"deposit");
            p.setDouble(3,amount);

            p.executeUpdate();
            System.out.println("deposit successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withdraw(long accountNumber, double amount) {
        try(Connection con=Util.getConnection())
        {
            con.setAutoCommit(false);
            PreparedStatement draw=con.prepareStatement("select balance  from accounts where account_number=?");
            draw.setLong(1,accountNumber);
            ResultSet rs=draw.executeQuery();
            if(rs.next())
            {
                Double curbalance=rs.getDouble("balance");

                if(curbalance<amount) {
                    System.out.println("insufficient balance");
                    return;
                }
                PreparedStatement update =con.prepareStatement("update accounts set balance=balance-? where account_number=?");
                update.setDouble(1,amount);
                update.setLong(2,accountNumber);
                update.executeUpdate();
                int accId=AccountDAO.getAccountId(con,accountNumber);
                PreparedStatement t=con.prepareStatement("insert into transactions(account_id,transaction_type,amount) values(?,?,?)");
                t.setLong(1,accId);
                t.setString(2,"withdraw");
                t.setDouble(3,amount);
                t.executeUpdate();
                System.out.println("withdraw successful");


            }
            else {
                System.out.println("Account not found");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkBalance(long accountNumber) {
        try(Connection con=Util.getConnection())
        {
            PreparedStatement p=con.prepareStatement("select balance from accounts  where account_number=?");
            p.setLong(1,accountNumber);
            ResultSet rs=p.executeQuery();
            if(rs.next())
            {
                Double curBalance=rs.getDouble("balance");
                System.out.println(curBalance);
            }
            else {
                System.out.println("insufficient balance");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void miniStatement(long accNo) {

        try (Connection con = Util.getConnection()) {

            int accId = AccountDAO.getAccountId(con, accNo);

            String sql = "SELECT transaction_type, amount, transaction_date " +
                    "FROM transactions WHERE account_id=? " +
                    "ORDER BY transaction_date DESC LIMIT 5";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accId);

            ResultSet rs = ps.executeQuery();

            System.out.println("=== MINI STATEMENT ===");

            while (rs.next()) {
                System.out.println(
                        rs.getString("transaction_type") + " | " +
                                rs.getDouble("amount") + " | " +
                                rs.getTimestamp("transaction_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void viewTransactions() {
        try (Connection con = Util.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from transactions");
            System.out.println(" transaction_id   |  account_id |   transaction_type  |    amount   |   transaction_date");
            while (rs.next()) {
                int  transaction_id  = rs.getInt("transaction_id");
                int account_id = rs.getInt("account_id");
                String  transaction_type  = rs.getString("transaction_type");
                Double amount  = rs.getDouble("amount");
                Date transaction_date = rs.getDate("transaction_date");
                System.out.println( transaction_id  + " " + account_id + " " +  transaction_type  + " " + amount  + " "+ transaction_date);



            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
