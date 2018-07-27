package cn.tanlw.java.think;

/**
 * 引用数据类型直接打印的话就是类名@地址值 -- https://zhidao.baidu.com/question/2120638214865706667.html
 * @create 2018/7/6
 */
public class HashCodeTest {
    public static void main(String[] args) {
        Object o = new Object();
        String a = "a";
        /**
         * 97
         */
        System.out.println(a.hashCode());
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        /**
         * false
         */
        System.out.println(sb1.hashCode() == sb2.hashCode());
        StringBuilder sb3 = sb2;
        /**
         * true
         */
        System.out.println(sb3.hashCode() == sb2.hashCode());
    }
}
