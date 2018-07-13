package net.csdn.songkai320;

/**
 * Java中的泛型<T>类与方法
 * https://blog.csdn.net/songkai320/article/details/51822497/
 * @create 2018/7/13
 */
public class GenericsTest<T> {
    private T x;

    public GenericsTest(T x) {
        this.x = x;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }


    public static void main(String[] args) {

        GenericsTest<String> strFoo = new GenericsTest<String>("Hello Generics!");

        GenericsTest<Double> douFoo = new GenericsTest<Double>(new Double("33"));

        GenericsTest<Object> objFoo = new GenericsTest<Object>(new Object());

        System.out.println("strTest.getX=" + strFoo.getX());

        System.out.println("douTest.getX=" + douFoo.getX());

        System.out.println("objTest.getX=" + objFoo.getX());


        GenericsTest<Byte> byteFoo = new GenericsTest<>(new Byte("1"));

        System.out.println("byteFoo.getX=" + byteFoo.getX());

        GenericsTest obj = new GenericsTest(new Object());

        //实际上，当构造对象时不指定类型信息的时候，默认会使用Object类型，这也是要强制转换的原因.
        System.out.println(obj.getX());

    }
}
