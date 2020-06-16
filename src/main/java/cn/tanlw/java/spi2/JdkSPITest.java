package cn.tanlw.java.spi2;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @Creator Tan Liwei
 * @Date 2020/6/15 19:56
 */
public class JdkSPITest {
    @Test
    public void test(){
        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        serviceLoader.forEach(Developer::sayHi);
    }
}
