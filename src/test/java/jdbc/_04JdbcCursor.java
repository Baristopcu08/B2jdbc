package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _04JdbcCursor {

    protected Connection conn;
    protected Statement stmt;
    protected ResultSet rs;


    @BeforeTest
    public void beforeTest() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "jdbc";
        String password = "jdbc123456";

        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }


    @Test
    public void test1() throws SQLException {
        String sql = "SELECT id, first_name, last_name, age, length" +
                " FROM personel" +
                //" ORDER BY first_name, last_name" +
                " LIMIT 50;";

        rs = stmt.executeQuery(sql);

        rs.next();  // cursor 1 kayit ileri gider
        Object[] arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        rs.next();
        rs.next();
        rs.next();
        arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        rs.previous();  // cursor 1 kayit geri gider
        arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        rs.last();  // cursor son kayda gider
        arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        rs.first(); // cursor ilk kayda gider
        arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        rs.absolute(10); // cursor tablodaki 10. kayda gider
        arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        rs.relative(3); // cursor bulundugu yerden baslamak üzere 3 kayit ileri gider
        arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        rs.relative(-3); // cursor bulundugu yerden baslamak üzere 3 kayit ileri gider
        arr = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

    }


    @AfterTest
    public void afterTest() throws SQLException {
        stmt.close();
        conn.close();
    }


}
