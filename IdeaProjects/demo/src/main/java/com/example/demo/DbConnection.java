package com.example.demo;
import  java.sql.*;
public class DbConnection {
    private final String dbURL="jdbc:mysql://localhost:3306/ecommerce";
    private final String userName="root";

    private  final String password="Rohit@1997";
    private  Statement getStatement(){
 try {
     Connection connection=DriverManager.getConnection(dbURL,userName,password);
     return  connection.createStatement();
 } catch (SQLException e) {
     e.printStackTrace();
 }
 return null;
    }
    public ResultSet getQueryTable(String query){
 try {
     Statement statement=getStatement();
     return statement.executeQuery(query);
 } catch (SQLException e) {
     e.printStackTrace();
 }
 return null;
    }
    public int updateDatabase(String query){
        try {
            Statement statement=getStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DbConnection conn=new DbConnection();
        ResultSet rs= conn.getQueryTable("SELECT*FROM customer");
        if(rs!=null){
            System.out.println("Connection is Succesfull");
        }else System.out.println("Connection is Failed");
    }
}
