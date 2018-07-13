package net.csdn.yizhixiaoxiaoniu;

/**
 * java 中类初始化，构造方法，静态成员变量，静态块的加载顺序 -- https://blog.csdn.net/a1028253086/article/details/59729072
 *
 * @create 2018/7/3
 */
public class StaticTest {
    public static void main(String[] args) {
        /**
         * 控制台输出
         =====静态成员变量========
         =====静态块======
         =====构造方法=====
         */
        new Abc();
        /**
         * 控制台输出
         =====构造方法=====
         */
        new Abc();

    }
}


class Abc {
    public static Bcd i = new Bcd();

    static {
        int b = 2;
        System.out.println("=====静态块======");
    }


    Abc() {
        System.out.println("=====构造方法=====");
    }

    public static void c() {
        System.out.println("=====静态方法===========");
    }
}


class Bcd {
    Bcd() {
        System.out.println("=====静态成员变量========");
    }
}
