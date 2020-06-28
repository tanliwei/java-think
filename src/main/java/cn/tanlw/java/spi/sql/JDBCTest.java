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
        /*
            注意：在JDBC 4.0规范中，这里可以不用再像以前那样编写显式加载数据库的代码了
            Class.forName("com.mysql.jdbc.Driver");
            获取数据库连接，注意【这里将会加载mysql的驱动包】
         */
        try {// META-INF/services/java.sql.Driver
            // java.util.ServiceLoader#nextService 中实例化 com.mysql.jdbc.Driver 驱动类，
            // 驱动类的静态代码块又把自己身 注册入 java/sql/DriverManager.java:358 中
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
            //
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
