package com.tuling.classloader;

/**
 * @Creator Tan Liwei
 * @Date 2018/10/6 14:31
 */
public class TestDemo2 {

    public static void main(String[] args) {
        //系统类加载器， 加载本项目的路径下的class
        MyClassLoader loader = new MyClassLoader("ZhangFei", "D:/tmp/");
        try {
            Class<?> c = loader.loadClass("com.tuling.classloader.Demo");
            /**
             * Console:
             * 2
             * A Demo
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

