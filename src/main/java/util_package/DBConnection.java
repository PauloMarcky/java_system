/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util_package;
import java.sql.Connection;
import java.sql.DriverManager;
 import java.sql.SQLException;
/**
 *
 * @author paulo
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/tuition_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";       // <-- change to your DB user
    private static final String PASSWORD = "ampogiko25"; // <-- change to your DB password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL Connector/J 8.x
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Add connector jar to classpath.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

