package com.tuling.classloader;

import java.io.*;

/**
 * @Creator Tan Liwei
 * @Date 2018/10/6 14:05
 */
public class MyClassLoader extends ClassLoader {

    private String path;//加载类的路径
    private String name;//类加载器的名称

    public MyClassLoader(String name, String path){
        super();//没有设置任何值，让系统加载器，成为该类的父类
        this.name = name;
        this.path = path;
    }

    public MyClassLoader(ClassLoader parent, String name, String path){
        super(parent);// 显式指定父类加载器
        this.name = name;
        this.path = path;
    }

    /**
     * 记载我们自定义的类，通过自定义的class
     * 先要读取我们的类文件
     * @param name com.tuling.classloader.Demo
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = readClassFileToByteArray(name);
        return this.defineClass(name, data, 0, data.length);
    }

    /**
     * 获取.class字节数组
     * com.tuling.classloader.Demo ->
     * F:/temp/tuling/classloader/Demo.class
     * @param name
     * @return
     */
    private byte[] readClassFileToByteArray(String name) {
        InputStream is = null;
        byte [] returnData = null;
        name = name.replaceAll("\\.","/");
        String filepath = this.path + name +".class";
        File file = new File(filepath);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(file);
            int tmp = 0;
            while((tmp = is.read())!= -1){
                os.write(tmp);
            }
            returnData = os.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnData;
    }

    @Override
    public String toString() {
        return name;
    }
}
