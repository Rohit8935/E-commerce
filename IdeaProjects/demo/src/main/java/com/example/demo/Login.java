package com.example.demo;

import java.sql.ResultSet;

public class Login {
 public Customer customerLogin(String userName,String password){
     String loginquerry= "SELECT * FROM customer WHERE EMAIL='"+userName+ "'AND PWD='"+password+"' ";
     DbConnection conn=new DbConnection();
     ResultSet rs= conn.getQueryTable(loginquerry);
     try {
        if(rs.next())
            return new Customer(rs.getInt("ID"
            ), rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("MOBILE"));

     } catch (Exception e){
         e.printStackTrace();
     }
     return null;
 }

    public static void main(String[] args) {
        Login login=new Login();
        Customer customer=login.customerLogin("RR123@gmail.com","122");
        System.out.println("Welcome : "+ customer.getName());
    }
}
