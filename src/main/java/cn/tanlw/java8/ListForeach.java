package cn.tanlw.java8;

import java.util.ArrayList;
import java.util.List;

public class ListForeach {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i+"c");
        }
        list.iterator().forEachRemaining( item ->{
            System.out.println(item);
        });
    }
}
