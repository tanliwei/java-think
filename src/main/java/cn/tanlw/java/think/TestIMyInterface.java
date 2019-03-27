package cn.tanlw.java.think;

import org.junit.Test;

public class TestIMyInterface {
    @Test
    public void test(){
        System.out.println(IMyInterface.MyEnum.A + " " + IMyInterface.MyEnum.A.ordinal());
        System.out.println(IMyInterface.MyEnum.B + " " + IMyInterface.MyEnum.B.ordinal());
    }
}
