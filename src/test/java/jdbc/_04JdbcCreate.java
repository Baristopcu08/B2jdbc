package jdbc;

import base.BaseConnection;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _04JdbcCreate extends BaseConnection {


    @Test
    public void test1() throws SQLException {
         /*
            sql = "CREATE TABLE mytable (
                first_name TEXT,
                last_name TEXT,
                age INTEGER
            )";
        */

        String sql = "CREATE TABLE mytable (" +
                "first_name VARCHAR(50)," +
                "last_name VARCHAR(50)," +
                "age INTEGER" +
                ");";
        System.out.println(stmt.execute(sql));

    }

    @Test
    public void test2Insert() throws SQLException {
        String sql = "INSERT INTO" +
                " mytable(first_name, last_name, age)" +
                " VALUES('Ali', 'Ali1', 12)";
        int num = stmt.executeUpdate(sql);
        System.out.println(num);

    }

    @Test
    public void test3Insert() throws SQLException {
        String sql = "INSERT INTO" +
                " mytable(first_name, last_name, age)" +
                " VALUES" +
                " ('Veli', 'Veli1', 12)," +
                " ('Veli', 'Veli1', 22), " +
                " ('Veli', 'Veli2', 32), " +
                " ('Veli', 'Veli3', 42)";
        int num = stmt.executeUpdate(sql);
        System.out.println(num);

    }

    @Test
    public void test4Select() throws SQLException {
        String sql = "SELECT * FROM mytable";
        rs = stmt.executeQuery(sql);
        while (rs.next()){
            System.out.printf("%-10s%-10s%d\n", rs.getString(1), rs.getString(2), rs.getInt(3));
        }

    }

    @Test
    public void test5Update() throws SQLException {
        String sql = "UPDATE mytable" +
                " SET first_name='Hasan', age=55" +
                " WHERE last_name='Veli1'";
        int num = stmt.executeUpdate(sql);
        System.out.println(num);
    }


    @Test
    public void test6PrepareStatement() throws SQLException {
        String sql = "INSERT INTO" +
                " mytable(first_name, last_name, age)" +
                " VALUES(?, ?, ?)";
        PreparedStatement stmt1 = conn.prepareStatement(sql);






    }


}
