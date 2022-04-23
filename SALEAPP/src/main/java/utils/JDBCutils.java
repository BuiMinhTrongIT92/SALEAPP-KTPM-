/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class JDBCutils {
    private static Connection Conn;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the Conn
     */
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/saleappktpm2", "root", "123456789");
    }
}
