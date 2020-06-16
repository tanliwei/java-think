package cn.tanlw.java.spi.demo;

/*

 */
public class PythonDeveloper implements Developer {
    @Override
    public void sayHi() {
        System.out.println("Say hi from a Python developer");
    }
}
