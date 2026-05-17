package com.bank.util;
import java.sql.Connection;
import java.sql.DriverManager;



    public class Util {

        private static final String URL = "jdbc:mysql://localhost:3306/bank";
        private static final String USER = "root";
        private static final String PASS = "1234";

        public static Connection getConnection() {
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","1234");
            } catch (Exception e) {
                throw new RuntimeException("DB Connection Failed");
            }
        }
    }

