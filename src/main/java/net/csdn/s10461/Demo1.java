package net.csdn.s10461;



import java.util.ArrayList;
import java.util.List;

/**
 *
 java 泛型详解-绝对是对泛型方法讲解最详细的，没有之一
 * https://blog.csdn.net/s10461/article/details/53941091
 * @create 2018/7/13
 */
public class Demo1 {
    public static void main(String[] args) {
        List arrayList = new ArrayList();
        arrayList.add("aaaa");
        arrayList.add(100);

        for(int i = 0; i< arrayList.size();i++){
            //Console: java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
            String item = (String)arrayList.get(i);
            System.out.println(("泛型测试"+"item = " + item));
        }

        List<String> arrayList2 = new ArrayList<String>();
        //下行代码，在编译阶段，编译器就会报错
//        arrayList2.add(100);

    }
}
