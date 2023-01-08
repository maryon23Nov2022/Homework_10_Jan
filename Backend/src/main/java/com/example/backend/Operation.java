package com.example.backend;

import java.sql.*;

public class Operation{
    private static final String url = "jdbc:mysql://localhost:3306/homework?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useServerPrepStmts=true";

    public static void addUser() throws SQLException{
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        String sql = "insert into user(username, password, identity) values('jack', '123456', 'student');";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    public static void selectUser() throws SQLException{
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        String sql = "select * from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            Integer id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            String identity = resultSet.getString(4);
            System.out.println(id);
            System.out.println(username);
            System.out.println(password);
            System.out.println(identity);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    public static boolean userLogin(String username, String password) throws SQLException{
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return resultSet.next();
    }
}
