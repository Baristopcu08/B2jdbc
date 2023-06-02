package jdbc;

import base.BaseSqlite;
import org.testng.annotations.Test;

import java.sql.*;

public class _06SQLite extends BaseSqlite {


    @Test
    public void test1() throws SQLException {
        // personel, job tablosunun özellikleri

        String sql = "SELECT * FROM personel;";
        rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println(":::::" + rsmd.getTableName(1));
        for (int i = 0; i < rsmd.getColumnCount(); i++) {
            System.out.println(rsmd.getColumnName(i + 1) + " : " + rsmd.getColumnTypeName(i + 1));
        }

        String sql1 = "SELECT * FROM job";
        ResultSet rs1 = stmt.executeQuery(sql1);
        ResultSetMetaData rsmd1 = rs1.getMetaData();
        System.out.println(":::::" + rsmd1.getTableName(1));
        for (int i = 0; i < rsmd1.getColumnCount(); i++) {
            System.out.println(rsmd1.getColumnName(i + 1) + " : " + rsmd1.getColumnTypeName(i + 1));
        }
    }

    @Test
    public void test2() throws SQLException {
        // personel, job tablosu : "Software Test Engineer" Meslegi yapanlarin sayilarini
        // job_id, id

        String sql = "SELECT jobtitle, COUNT(*) AS sayi" +
                " FROM personel" +
                " LEFT JOIN job ON personel.job_id = job.id" +
                " WHERE jobtitle LIKE '%Software Test Engineer%'" +
                " GROUP BY jobtitle;";

        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.printf("%-30s%d\n", rs.getString("jobtitle"), rs.getInt("sayi"));
        }

    }


    @Test
    public void test3() throws SQLException {
        /*
            sql = "CREATE TABLE mytable (
                first_name TEXT,
                last_name TEXT,
                age INTEGER
            )";
        */

        String sql = "CREATE TABLE mytable (" +
                "first_name TEXT," +
                "last_name TEXT," +
                "age INTEGER" +
                ");";
        System.out.println(stmt.execute(sql));
    }


}
