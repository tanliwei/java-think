package cn.tanlw.java.extend;

public class Test {

    public static void main(String[] args) {
        Son s = new Son();
        //能继承父类的 缺省修饰符方法
        s.defaultMethod();
        //取消下行注释 编译出错 不能继承父类的私有方法
        //s.parentPrivateMethod();
        s.publicMethod();
        //取消下行注释 编译出错
        //s.privateMethod();

    }
}
