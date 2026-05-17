package com.bank.dao;
import java.sql.*;
public class AccountDAO {
    public static int getAccountId(Connection con, long accountNumber) throws Exception {
        PreparedStatement ps = con.prepareStatement("select account_id from accounts where account_number=?");
        ps.setLong(1, accountNumber);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
   {
       return rs.getInt("account_id");

   }
throw new RuntimeException("Account not found");
    }

}
