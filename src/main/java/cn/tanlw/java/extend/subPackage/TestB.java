package cn.tanlw.java.extend.subPackage;

public class TestB {

    public static void main(String[] args) {
        SonB s = new SonB();
        //下行编译出错，不能再外部包访问
//        s.defaultMethod();
        //下行编译出错 has protected access
//        s.protedtedMethod();
        //下行编译出错 不能继承父类的私有方法
        //s.parentPrivateMethod();

        s.sonProtectedMethod();
        s.publicMethod();
        //下行 编译出错
        //s.privateMethod();

    }
}
