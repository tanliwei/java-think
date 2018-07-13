package net.csdn.s10461;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2018/7/13
 */
public class DemoCompileTime {
    public static void main(String[] args) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        /**
         * 在编译过程中，正确检验泛型结果后，
         * 会将泛型的相关信息擦出，
         * 并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。
         * 也就是说，泛型信息不会进入到运行时阶段。
         */
        if(classStringArrayList.equals(classIntegerArrayList)){
            //Console: 类型相同
            System.out.println("类型相同");
            System.out.println(classIntegerArrayList.getName());
            System.out.println(classIntegerArrayList.getName());
        }
    }
}
