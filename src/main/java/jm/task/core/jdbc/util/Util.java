package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "7x87rZ8E.";
    public static final String URL = "jdbc:mysql://localhost:3306/kata_pp";

public Statement getConnection() {
    Connection connection = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        if (connection != null) {
           return connection.createStatement();
        }
        System.out.println("Ощибка подключения!");
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        System.err.println("Connection ERR");
    }
    return null;
}

}
