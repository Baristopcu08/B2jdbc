package jdbc;

import base.BaseConnection;
import org.testng.annotations.Test;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class _03JdbcMetaData extends BaseConnection {


    @Test
    public void test1() throws SQLException {
        // personel tablosu, shirtsize'lara göre ortalama yas,
        // Max yas, min yas listesini consola yazdirin
        String sql = "SELECT shirtsize, AVG(age) AS ortalama_yas, MIN(age) AS min_age, MAX(age) AS max_age" +
                " FROM personel" +
                " GROUP BY shirtsize" +
                " ORDER BY shirtsize;";

        rs = stmt.executeQuery(sql);
        /*
        rs.next();
        System.out.println(
                rs.getString(1) + "\t" +
                        rs.getDouble(2) + "\t" +
                        rs.getInt(3) + "\t" +
                        rs.getInt(4)
        );

        rs.next();
        System.out.println(
                rs.getString(1) + "\t" +
                        rs.getDouble(2) + "\t" +
                        rs.getInt(3) + "\t" +
                        rs.getInt(4)
        );
         */

        while (rs.next()){
            System.out.println(
                    rs.getString(1) + "\t" +
                            rs.getDouble(2) + "\t" +
                            rs.getInt(3) + "\t" +
                            rs.getInt(4)
            );
        }


    }


    @Test
    public void test2_MetaData() throws SQLException {
        String sql = "SELECT shirtsize, AVG(age) AS ortalama_yas, MIN(age) AS min_age, MAX(age) AS max_age" +
                " FROM personel" +
                " GROUP BY shirtsize" +
                " ORDER BY shirtsize;";

        rs = stmt.executeQuery(sql);

        // rs ile dönen tablo ile ilgili bilgiler MetaData icinde yer alir
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.println("-------------------");
            System.out.println(rsmd.getColumnName(i));
            System.out.println(rsmd.getColumnDisplaySize(i));
            System.out.println(rsmd.getColumnTypeName(i));
        }

    }


}
