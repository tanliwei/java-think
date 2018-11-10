package com.tuling.classloader;

import java.util.List;

/**
 * JVM类加载机制详解（二）类加载器与双亲委派模型
 * @author https://blog.csdn.net/zhangliangzi/article/details/51338291
 * 三个类“相等”判定条件:
 * 1、两个类来自同一个Class文件
 * 2、两个类是由同一个虚拟机加载
 * 3、两个类是由同一个类加载器加载
 *
 * 【深入Java虚拟机】之四：类加载机制 https://blog.csdn.net/ns_code/article/details/17881581
 *  类加载的过程包括了加载、验证、准备、解析、初始化（clinit）
 * @Date 2018/11/1 15:19
 */
/**
 * jar cvf test.jar com/tuling/classloader/ClassLoaderTest.class
 * 放到 %JAVA_HOME%/jre/lib/ext 目录下
 *
 * Console:
 * ClassLoaderText类的加载器的名称:sun.misc.Launcher$ExtClassLoader
 * System类的加载器的名称:null
 * List类的加载器的名称:null
 * sun.misc.Launcher$ExtClassLoader->null
 *
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        /**
         1、ClassLoaderTest类是用户定义的类，位于CLASSPATH下，由系统/应用程序类加载器加载。
         2、System类与List类都属于Java核心类，由祖先类启动类加载器加载，而启动类加载器是在JVM内部通过C/C++实现的，并不是Java，自然也就不能继承ClassLoader类，自然就不能输出其名称。
         3、而箭头项代表的就是类加载的流程，层级委托，从祖先类加载器开始，直到系统/应用程序类加载器处才被加载。
         ---------------------
         作者：leeon_l
         来源：CSDN
         原文：https://blog.csdn.net/zhangliangzi/article/details/51338291
         版权声明：本文为博主原创文章，转载请附上博文链接！
         */

        /**
         * Console:
         ClassLoaderText类的加载器的名称:sun.misc.Launcher$AppClassLoader
         System类的加载器的名称:null
         List类的加载器的名称:null
         sun.misc.Launcher$AppClassLoader->sun.misc.Launcher$ExtClassLoader->null
         */
        System.out.println("ClassLoaderText类的加载器的名称:"+ClassLoaderTest.class.getClassLoader().getClass().getName());
        System.out.println("System类的加载器的名称:"+System.class.getClassLoader());
        System.out.println("List类的加载器的名称:"+List.class.getClassLoader());

        ClassLoader cl = ClassLoaderTest.class.getClassLoader();
        while(cl != null){
            System.out.print(cl.getClass().getName() + "->");
            cl = cl.getParent();
        }
        System.out.println(cl);
    }
}
