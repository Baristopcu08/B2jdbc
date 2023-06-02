package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _05SQLite {



    protected Connection conn;
    protected Statement stmt;
    protected ResultSet rs;


    @BeforeTest
    public void beforeTest() throws SQLException {
        //String url = "jdbc:mysql://localhost:3306/db1";
        String url = "jdbc:sqlite:src/test/resources/data.sqlite";

        //conn = DriverManager.getConnection(url, "jdbc", "jdbc123456");
        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();
    }


    @Test
    public void test1() throws SQLException {
        String sql = "SELECT id, first_name, last_name, age, length" +
                " FROM personel" +
                " LIMIT 50;";

        rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Object[] arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
            System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);
        }

    }






    @AfterTest
    public void afterTest() throws SQLException {
        stmt.close();
        conn.close();
    }

}
