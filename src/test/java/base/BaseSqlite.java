package base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class BaseSqlite {

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




    @AfterTest
    public void afterTest() throws SQLException {
        stmt.close();
        conn.close();
    }
}
