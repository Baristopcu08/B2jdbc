package jdbc;

import java.sql.*;

public class _01Connection {

    /*
        Bir veritabani
        veritabani yolu, nerede, cloud, local
        baglanmak icin ayazüz, MySQL Workbench


        JDBC
        java ile veritabanlarina baglanma modülü

                    : Ankara - Istanbul
        Connection  : Yol, E5, TEM
        Statement   : firma
        ResultSet   : otobüs, data tasima

     */

    public static void main(String[] args) throws SQLException {

        // connection string = url
        // url : veritabani türürüne göre jdbc baglanti stringi dir
        //String url = "jdbc:mysql://[localhost | 105.12.167.214]:[PORT]/[database_name]";
        //String url = "jdbc:microsoft.sqlserver://[localhost | 105.12.167.214]:[PORT]/[DATABASE=database_name]";

        String url = "jdbc:mysql://localhost:3306/db1";

        String username = "jdbc";
        String password = "jdbc123456";

        // 1- veritabanina yapilan konnection
        Connection conn = DriverManager.getConnection(url, username, password);

        // 2- iletisim icin Statement olusturulmali
        Statement stmt = conn.createStatement();

        // 3- Data transfer icin Resultset gerekli
        ResultSet rs = stmt.executeQuery("SELECT * FROM table1");
        while (rs.next()){
            int id = rs.getInt(1);
            String firstName = rs.getString("first_name");
            String lastName = rs.getString(3);
            String gender = rs.getString(4);
            String age = rs.getString("age");
            /*
            String str = String.format("%d\t%-20s%-20s%-10s%-5s", id, firstName, lastName, gender, age);
            System.out.println(str);
             */
            System.out.printf("%d\t%-20s%-20s%-10s%-5s\n", id, firstName, lastName, gender, age);
        }
        /*
            stmt.execute();             -> return : boolean     : CREATE, ALTER , islemin yapilip yapilmadigini
            stmt.executeQuery();        -> return : Resultset   : SElECT, table return eder
            stmt.executeUpdate();       -> return : int         : DELETE, UPDATE. Etkilenen kayit sayisi
         */


        /*
            rs.getInt([index | fieldName]) -> db'deki dataType int olanlar icin
            rs.getString([index | fieldName]) -> db'deki dataType String olanlar icin
                                                 diger typelar da getString ile alinabilir, örn: age
         */


        stmt.close();
        conn.close();

    }


}
