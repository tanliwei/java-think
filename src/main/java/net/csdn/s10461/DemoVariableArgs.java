package net.csdn.s10461;

/**
 * 静态方法有一种情况需要注意一下，
 * 那就是在类中的静态方法使用泛型：静态方法无法访问类上定义的泛型；
 * 如果静态方法操作的引用数据类型不确定的时候，
 * 必须要将泛型定义在方法上
 * @create 2018/7/13
 */
public class DemoVariableArgs {
    public static <T> void printMsg( T... args){
        for(T t : args){
            Log.d("泛型测试","t is " + t);
        }
    }

    public static void main(String[] args) {
        printMsg("111",222,"aaaa","2323.4",55.55);
    }
}
