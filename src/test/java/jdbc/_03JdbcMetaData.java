package jdbc;

import base.BaseConnection;
import org.testng.annotations.Test;
import utils.Utils;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

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


    @Test
    public void test3() throws SQLException {
        // table1 tablosunu SELECT * FROM table1 ile alin.
        // tablo basligini ilk satira yazdirin
        // altina datali yazdirin

        String sql = "SELECT * FROM table1;";
        rs = stmt.executeQuery(sql);

        // sql ile gelen tablonun meta datasini aliyoruz
        ResultSetMetaData rsmd = rs.getMetaData();

        // xolon (field) yasisi alindi
        int cols = rsmd.getColumnCount();

        // field'larin display sizelarini kaydetmek icin bir array oluturuldu.
        int[] colSize = new int[cols];

        // field'larin display size'lari array'e kaydedildi
        for (int i = 0; i < cols; i++) {
            colSize[i] = rsmd.getColumnDisplaySize(i+1)/2;
        }

        // basliklar yazdiriliyor
        // System.out.printf("%-20s\t", "string"); -> string text'i icin 20 karakterlik yer ayrilacak ve sol yasli yazilacak sonra tab atilacak
        for (int i = 0; i < cols; i++) {
            System.out.printf("%-" + colSize[i] + "s", rsmd.getColumnName(i+1));
        }
        System.out.println();

        // while ile tüm datalari geziyoruz
        while (rs.next()) {
            // fieldlari satir icinde yaziyoruz.
            for (int i = 0; i < cols; i++) {
                System.out.printf("%-" + colSize[i] + "s", rs.getString(i + 1));
            }
            System.out.println();
        }

    }

    @Test
    public void test4(){
        String sql = "SELECT id, first_name, last_name, age FROM personel";
        List<List<String>> data = Utils.getTable(stmt, sql);

        for (List<String> row : data) {
            System.out.println(row);
        }

    }

}
