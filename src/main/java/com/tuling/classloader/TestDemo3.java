package com.tuling.classloader;


/**
 * D:\tmp\com\tuling\classloader 目录下新建文件 Demo.java （并编译）：
 package com.tuling.classloader;

 public class Demo{

 public Demo(){
 System.out.println("B Demo, " + this.getClass().getClassLoader());
 }
 }
 }
 */
/**
 * @Creator Tan Liwei
 * @Date 2018/10/6 14:31
 */
public class TestDemo3 {

    public static void main(String[] args) {
        //parent为null， 不会调用BootStrap ClassLoader，会调用MyClassLoader的findClass
        //Tomcat热加载类似这样做
        MyClassLoader wuKongLoader = new MyClassLoader(null,"wukong", "D:/tmp/");
        try {
            Class<?> c = wuKongLoader.loadClass("com.tuling.classloader.Demo");
            /**
             * Console:
             * B Demo, wukong
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

