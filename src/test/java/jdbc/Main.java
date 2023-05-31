package jdbc;

import utils.Utils;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<List<String>> lists = Utils.getTable("SELECT first_name, last_name FROM table1");

        for (List<String> list : lists) {
            System.out.println(list);
        }
    }
}
