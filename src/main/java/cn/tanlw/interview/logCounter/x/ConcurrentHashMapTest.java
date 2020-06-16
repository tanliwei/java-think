package cn.tanlw.interview.logCounter.x;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap concurrent = new ConcurrentHashMap();
        concurrent.put("A1", "A");
        concurrent.put("2B", "B");
        concurrent.put("C3", "C");
        concurrent.put("4D", "D");
        concurrent.put("5D", "D");
        concurrent.put("6D", "D");
        concurrent.put("7D", "D");
        concurrent.put("8D", "D");
        concurrent.put("9D", "D");
        concurrent.put("0D", "D");
        concurrent.put("D1", "D");
        concurrent.put("D2", "D");
    }
}
