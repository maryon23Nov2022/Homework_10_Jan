//package com.example.backend.mapper;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public interface UserMapper{
//    public boolean addUser() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/homework?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8";
//        String username = "root";
//        String password = "123456";
//        Connection connection = DriverManager.getConnection(url, username, password);
//
//        String sql = "INSERT INTO table_name(username, password, identity)\n" +
//                "VALUES('jack', '123456', 'student');";
//        Statement statement = connection.createStatement();
//
//        int count = statement.executeUpdate(sql);
//
//        statement.close();
//        connection.close();
//        System.out.println(count);
//        return count > 0;
//    }
//
//}
