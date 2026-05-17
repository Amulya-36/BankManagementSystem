package com.bank.service;

import com.bank.model.Customer;
import com.bank.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerServiceImpl implements CustomerService{
    @Override
    public void createCustomer(Customer c) {
        try(Connection con= Util.getConnection())
        {
            PreparedStatement ps=con.prepareStatement("insert into customers values(?,?,?,?,?,?)");
            ps.setInt(1, c.getCustomerId());
            ps.setString(2,c.getName());
            ps.setString(3,c.getPhone());
            ps.setString(4,c.getEmail());
            ps.setString(5,c.getAddress());
            ps.setInt(6,c.getUserId());
            ps.executeUpdate();
            System.out.println("customer added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void viewCustomers() {
        try (Connection con = Util.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customers");
            System.out.println("customer_id  |  customer_name  |  customer_phone  | customer_email  |  customer_address | userID");
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String customer_name = rs.getString("customer_name");
                String phone = rs.getString("customer_phone");
                String email = rs.getString("customer_email");
                String address = rs.getString("customer_address");
                int userId=rs.getInt("user_id");
                System.out.println(customer_id+" "+customer_name + " " + phone + " " + email + " " + address + " "+userId);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
