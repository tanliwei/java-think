package cn.tanlw.java.spi.demo;


import java.util.ServiceLoader;

/*

 */
public class ASPITester {

    public static void main(String[] args) {
        // META-INF/services/cn.tanlw.java.spi.demo.Developer
        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        serviceLoader.forEach(Developer::sayHi);
    }
}
