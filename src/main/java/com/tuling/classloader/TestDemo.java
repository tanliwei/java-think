package com.tuling.classloader;

/**
 * @Creator Tan Liwei
 * @Date 2018/10/6 14:31
 */
public class TestDemo {

    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader("ZhangFei", "D:/tmp/");
        try {
            Class<?> c = loader.loadClass("Demo");//调用这个类的构造方法
            /**
             * Console:
             * Demo, ZhangFei
             */
            c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
