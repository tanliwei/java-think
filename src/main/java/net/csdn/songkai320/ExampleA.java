package net.csdn.songkai320;

/**
 * https://blog.csdn.net/songkai320/article/details/51822497/
 * 是否拥有泛型方法，与其所在的类是否泛型没有关系。要定义泛型方法，只需将泛型参数列表置于返回值前
 * @create 2018/7/13
 */
public class ExampleA {
    public <T> T f(T x) {
        System.out.println(x.getClass().getName());
        return x;
    }
    public <T> void f2(Object x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        ExampleA a = new ExampleA();
        //下行代码报错：Incompatible types
//        int str = a.f("");

        System.out.println(a.f(" "));
        System.out.println(a.f(10));
        a.f2(" ");
        a.f2(10);
    }
}
