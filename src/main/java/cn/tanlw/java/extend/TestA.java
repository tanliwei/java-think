package cn.tanlw.java.extend;

public class TestA {

    public static void main(String[] args) {
        SonA s = new SonA();
        //能继承父类的 缺省修饰符方法 需要在同一个包下面
        s.defaultMethod();
        //能继承父类的 protected修饰符方法
        s.protedtedMethod();
        //取消下行注释 编译出错 不能继承父类的私有方法
        //s.parentPrivateMethod();
        s.publicMethod();
        //取消下行注释 编译出错
        //s.privateMethod();

    }
}
