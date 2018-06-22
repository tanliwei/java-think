package cn.tanlw.java.think;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2018/6/21
 */
public class ArrayListTest {
    public static void main(String[] args) {
        List a  = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            //int oldCapacity = elementData.length;
            //int newCapacity = oldCapacity + (oldCapacity >> 1);
            a.add(i);
        }
    }
}
