package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection{

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connect_mysql_connector?useTimezone=true&serverTimezone=UTC", "root", "12345");
        System.out.println("Connected to Database");
    }
}
