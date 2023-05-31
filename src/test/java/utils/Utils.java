package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {




    public static List<List<String>> getTable(String sql){
        List<List<String>> lists = new ArrayList<>();
        try {
            String url = "jdbc:mysql://localhost:3306/db1";

            String username = "jdbc";
            String password = "jdbc123456";

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                List<String> list = new ArrayList<>();
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                lists.add(list);
            }

            stmt.close();
            conn.close();
            return lists;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
