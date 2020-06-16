package cn.tanlw.java.spi2;

/**
 * @Creator Tan Liwei
 * @Date 2020/6/15 19:52
 */
public class JavaDeveloper implements Developer {

    @Override
    public void sayHi() {
        System.out.println("Say hi from a java developer!");
    }
}
