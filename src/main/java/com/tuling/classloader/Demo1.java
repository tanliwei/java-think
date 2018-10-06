package com.tuling.classloader;

/**
 * @Creator Tan Liwei
 * @Date 2018/10/6 13:21
 */
public class Demo1 {
    /**
     * 静态代码块的执行顺序，按照源码的顺序执行。
     */
    static {
        tmp = 2;
        //取消掉下行注释，编译报错，不能读取后面的静态变量，只能写。
//        System.out.println(tmp);
    }

    public static int tmp = 1;//tmp在执行这个语句之前值为0，非final

    public static void main(String[] args) {
        tmp = 3;
        System.out.println(tmp);
    }
}
