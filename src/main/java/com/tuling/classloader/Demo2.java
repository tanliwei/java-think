package com.tuling.classloader;

/**
 * @Creator Tan Liwei
 * @Date 2018/10/6 13:51
 */
public class Demo2 {
    public static void main(String[] args) {
        System.out.println(Demo2.class.getClassLoader());
        ClassLoader loader = Demo2.class.getClassLoader();
        /**
         * Console:
         * sun.misc.Launcher$AppClassLoader@18b4aac2
           sun.misc.Launcher$AppClassLoader@18b4aac2
           sun.misc.Launcher$ExtClassLoader@6842775d
           null
         */
        while (loader!=null ){
            System.out.println(loader);
            loader = loader.getParent();
        }
        System.out.println(loader);
    }
}
