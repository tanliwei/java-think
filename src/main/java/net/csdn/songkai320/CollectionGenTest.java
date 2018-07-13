package net.csdn.songkai320;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @create 2018/7/13
 */
public class CollectionGenTest<T extends Collection> {

    private T x;

    public CollectionGenTest(T x) {
        this.x = x;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public static void main(String[] args) {
        // 下面这行代码编译出错： should implement Collection
//        CollectionGenTest<Object> a;
        CollectionGenTest<ArrayList> myArr = null;
        //下面这行代码编译出错： can not infer argument
//        myArr = new CollectionGenTest<>(new LinkedList());
        myArr = new CollectionGenTest<>(new ArrayList());
        myArr.getX().add("a");
        System.out.println(myArr.getX().get(0));
        // 需要将CollectionGenTest<Collection>改为CollectionGenTest<ArrayList>
//        CollectionGenTest<Collection> listTest = null;
//        listTest=new CollectionGenTest<ArrayList>(new ArrayList());

    }
}
