package com.bank.service;

import com.bank.model.User;
import com.bank.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationServiceImpl implements AuthenticationService{
    @Override
    public User login(String username, String password) {
        try(Connection con= Util.getConnection())
        {
            PreparedStatement p=con.prepareStatement("select * from users where username=? AND password=?");
            p.setString(1,username);
            p.setString(2,password);
            ResultSet rs=p.executeQuery();
            if(rs.next())
            {
                User user=new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));


                return user;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
