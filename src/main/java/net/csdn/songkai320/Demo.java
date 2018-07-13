package net.csdn.songkai320;

/**
 * @create 2018/7/13
 */
public class Demo {
    //一个static方法，无法访问泛型类的类型参数，所以，若要static方法需要使用泛型能力，必须使其成为泛型方法。
    public static <T> T test(T a){
        return a;
    }
}
