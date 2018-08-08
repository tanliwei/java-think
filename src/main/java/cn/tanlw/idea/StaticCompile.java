package cn.tanlw.idea;

import java.util.List;

/**
 * @create 2018/8/3
 */
public class StaticCompile {
    static List list;
    static {
        //运行时出错
        //java.lang.NullPointerException
        list.add("a");
    }

    public static void main(String[] args) {
        List list;
        //编译器出错
        //变量未初始化
//        list.add("a");
    }
}
