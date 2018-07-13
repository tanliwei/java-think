package net.csdn.s10461;

/**
 * @create 2018/7/13
 */
public class GenericWildcard {
    public static void showKeyValue(Generic<Number> obj){
        Log.d("泛型测试","key value is " + obj.getKey());
    }

    /**
     * 此处’？’是类型实参，而不是类型形参 ！
     * 再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，
     * 可以把？看成所有类型的父类。是一种真实的类型。
     * @param obj
     */
    public static void showKeyValue1(Generic<?> obj){
        Log.d("泛型测试","key value is " + obj.getKey());
    }

    public static void main(String[] args) {
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

        showKeyValue(gNumber);
//同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。
// showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer>
// cannot be applied to Generic<java.lang.Number>
//// showKeyValue(gInteger);

         showKeyValue1(gInteger);

    }
}
