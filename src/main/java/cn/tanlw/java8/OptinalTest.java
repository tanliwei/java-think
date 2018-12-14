package cn.tanlw.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * http://www.runoob.com/java/java8-optional-class.html
 *
 * @Creator Tan Liwei
 * @Date 2018/10/18 16:01
 */
public class OptinalTest {
    public static void main(String[] args) {
        OptinalTest test = new OptinalTest();
        Integer value1= null;
        Integer value2 = new Integer(10);

        //运行传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        //不允许null
        Optional<Integer> b = Optional.of(value2);
        System.out.println(test.sum(a, b));
        /** Console:
         value1是否存在:false
         value2是否存在:true
         10
         */
    }

    private Integer sum(Optional<Integer> a, Optional<Integer> b) {

        //是否存在
        System.out.println("value1是否存在:"+a.isPresent());
        System.out.println("value2是否存在:"+b.isPresent());

        //如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //获取值，需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }

    @Test(expected=NullPointerException.class)
    public void testOf(){
        Integer a = null;
        Optional<Integer> value1 = Optional.of(a);
    }

    @Test(expected = NullPointerException.class)
    public void testOrElseThrow(){
        Object o = null;
        Optional.ofNullable(o).orElseThrow(NullPointerException::new);
    }
}
