package cn.tanlw.java.spi.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/*

 */
public class JDBCTest {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {//META-INF/services/java.sql.Driver
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from user");
            while (rs.next()) {
                String name = rs.getString("User");
                System.out.println("name:" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
