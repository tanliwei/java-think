package com.tuling.classloader;

/**
 * @Creator Tan Liwei
 * @Date 2018/10/6 13:21
 */
public class Demo {
    public static int tmp = 1;//tmp 在执行这个语句之前值为0，非final
    static {
        tmp = 2;
        System.out.println(tmp);
    }

    public Demo(){
        System.out.println("A Demo");
    }
    public static void main(String[] args) {
        tmp = 3;
        System.out.println(tmp);
    }
}
