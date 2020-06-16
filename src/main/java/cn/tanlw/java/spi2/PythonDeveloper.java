package cn.tanlw.java.spi2;

/**
 * @Creator Tan Liwei
 * @Date 2020/6/15 19:53
 */
public class PythonDeveloper implements Developer {

    @Override
    public void sayHi() {
        System.out.println("Say hi from a python developer!");
    }

}
