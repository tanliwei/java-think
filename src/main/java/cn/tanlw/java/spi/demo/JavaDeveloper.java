package cn.tanlw.java.spi.demo;

/*

 */
public class JavaDeveloper implements Developer{

    @Override
    public void sayHi() {
        System.out.println("Say hi from a Java developer");
    }
}
