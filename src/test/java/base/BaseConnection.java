package base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class BaseConnection {

    protected Connection conn;
    protected Statement stmt;
    protected ResultSet rs;


    @BeforeTest
    public void beforeTest() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "jdbc";
        String password = "jdbc123456";

        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement();
    }


    @AfterTest
    public void afterTest() throws SQLException {
        stmt.close();
        conn.close();
    }

}
