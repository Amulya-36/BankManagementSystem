package com.bank.dao;
import java.sql.*;
import com.bank.util.Util;

public class UserDAO {
    public static int getUserId(String username) throws Exception{

        String sql = "SELECT user_id FROM users WHERE username=?";

        try (Connection con = Util.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            }

        }

        throw new RuntimeException("User not found");
    }

}

