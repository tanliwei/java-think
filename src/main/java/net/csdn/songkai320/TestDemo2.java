package net.csdn.songkai320;

/**
 * @create 2018/7/13
 */
class Test2{

    private Object ob; // 定义一个通用类型成员

    public Test2(Object ob) {

        this.ob = ob;

    }
    public Object getOb() {
        return ob;
    }
    public void setOb(Object ob) {
        this.ob = ob;
    }
    public void showTyep() {
        System.out.println("T的实际类型是: " + ob.getClass().getName());
    }
}

public class TestDemo2 {
    public static void main(String[] args) {
        // 定义类Gen2的一个Integer版本
        Test2 intOb = new Test2(new Integer(88));
        intOb.showTyep();
        int i = (Integer) intOb.getOb();
        System.out.println("value= " + i);
        System.out.println("---------------------------------");
        // 定义类Gen2的一个String版本
        Test2 strOb = new Test2("Hello Gen!");
        strOb.showTyep();
        String s = (String) strOb.getOb();
        System.out.println("value= " + s);
        System.out.println("---------------------------------");

        Test2 i2 = new Test2(1);
        i2.showTyep();
        int i2_ = (int)i2.getOb();
        System.out.println(i2_);

        System.out.println("---------------------------------");
        Test2 doubleObj = new Test2(1.1D);
        doubleObj.showTyep();
        double d = (double)doubleObj.getOb();
        System.out.println(d);
    }
}
