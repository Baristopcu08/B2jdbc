package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _02Jdbc {
    /*
        BeforeTest: Connection, Statement tanimlayin
        AfterTest : Connection, Statement close edin.
        Test1 : gender'larin sayilarini consola yazdirin
     */

    Connection conn;
    Statement stmt;
    ResultSet rs;

    //String url = "jdbc:mysql://localhost:3306/db1";    // localhost = 127.0.0.1
    //String url = "jdbc:mysql://127.0.0.1:3306/db1";

    //String url = "jdbc:mysql://[username]:[password]@IP:PORT/db_name";
    String url = "jdbc:mysql://jdbc:jdbc123456@127.0.0.1:3306/db1";

    @BeforeTest
    public void beforeTest() throws SQLException {
        String username = "jdbc";
        String password = "jdbc123456";

        //conn = DriverManager.getConnection(url, username, password);
        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();
    }


    @Test
    public void test1() throws SQLException {
        String sql = "SELECT gender, COUNT(*) AS sayi" +
                " FROM personel" +
                " GROUP BY gender" +
                " ORDER BY sayi DESC";
        rs = stmt.executeQuery(sql);

        while (rs.next()){
            System.out.printf("%-15s:%d\n", rs.getString("gender"), rs.getInt("sayi"));
        }

    }


    @Test
    public void test2() throws SQLException {
        String sql = "SELECT gender, COUNT(*) AS sayi" +
                " FROM personel" +
                " GROUP BY gender" +
                " UNION" +
                " SELECT 'Toplam', COUNT(*) FROM personel";
        rs = stmt.executeQuery(sql);

        while (rs.next()){
            System.out.printf("%-15s: %d\n", rs.getString("gender"), rs.getInt("sayi"));
        }

    }



    @Test
    public void test3() throws SQLException {
        /*
            Adinizi, soyadinizi, cinsiyet ve yasinizi table1 tablosuna ekleyin
            Eklenen kaydi SELECT ile alin ve konsola yazdirin
            INSERT : stmt.executeUpdate(sql);
            SELECT : stmt.executeQuery(sql);

            INSERT INTO table1(first_name, last_name, gender, age) VALUES('Ahmet', 'T', 'male', 50);
         */

        String sql = "INSERT INTO table1(first_name, last_name, gender, age) VALUES('AhmetT', 'TT', 'male', 50);";
        int num = stmt.executeUpdate(sql);
        if (num > 0){
            System.out.println("Kayit yapildi");
            sql = "SELECT * FROM table1 ORDER BY id DESC LIMIT 1";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        }else {
            System.out.println("Kayit yapilamadi #############");
        }
    }



    @AfterTest
    public void afterTest() throws SQLException {
        stmt.close();
        conn.close();
    }



}
