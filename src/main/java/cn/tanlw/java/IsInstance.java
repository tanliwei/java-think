package cn.tanlw.java;

public class IsInstance {

    public static void main(String[] args) {
        A a = new A();
        A b = new B();
        A c = new C();
        //true
        System.out.println(A.class.isInstance(b));
        //true
        System.out.println(A.class.isInstance(c));
    }

    private static class A {
    }

    private static class B extends A {
    }

    private static class C extends A {
    }
}
