package cn.tanlw.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * Function接口 – Java8中java.util.function包下的函数式接口
 * http://ifeve.com/jjava-util-function-java8/
 *
 * @Creator Tan Liwei
 * @Date 2018/10/18 16:01
 */
public class FunctionDemo {

    @Test
    public void identityTest() {
        List<String> collect = Arrays.asList("a", "b", "c")
                .stream()
                .map(Function.identity())
                .collect(Collectors.toList());
        /**
         * OUTPUT:
         * collect:[a, b, c]
         */
        out.println("collect:"+collect);
        List<String> collectB = collect
                .stream()
                .map(x -> x = x + "B")
                .collect(Collectors.toList());
        /**
         * OUTPUT:
         * collectB:[aB, bB, cB]
         */
        out.println("collectB:"+collectB);

        String collectC = collect
                .stream()
//                .map(x -> x = x + "C")
                .collect(Collectors.joining(":"));
        /**
         * OUTPUT:
         * collectC:a:b:c
         */
        out.println("collectC:"+collectC);
        /**
         * OUTPUT:
         * a:b:c
         */
        out.println(String.join(":", collect));

        Map<String, String> collectD = collect
                .stream()
                .collect(Collectors.toMap(k->k, v->v));
        //collectD:{a=a, b=b, c=c}
        out.println("collectD:"+collectD);
        /**
         * OUTPUT:
         * a:b:c:
         */
        collectD.entrySet().stream().forEach(item -> out.print(item.getKey()+":"));

        collectD.entrySet().forEach( item -> {
            System.out.println("Let's do first thing");
            item.setValue(item.getValue()+"INNER");
            int i = 0;
            i = i++;
            //do sth as it in a method.
        });
        //collectD:{a=aINNER, b=bINNER, c=cINNER}
        System.out.println("collectD:"+collectD);
    }

    static int modifyTheValue(int oper, Function<Integer, Integer> function) {
        int ret = function.apply(oper);
        out.println(ret);
        return ret;
    }

    public static void main(String[] args) {
        int incr = 20;
        int myNumber = 10;
        modifyTheValue(myNumber, val -> val + incr);
        myNumber = 15;
        modifyTheValue(myNumber, val -> val * 10);
        modifyTheValue(myNumber, val -> val - 100);
        modifyTheValue(myNumber, val -> "somestring".length() + val - 100);
        /**
         * Console:
         30
         150
         -85
         -75
         */
    }

    @Test
    public void testApply() {
        int myNumber = 10;
        int res1 = modifyTheValue(myNumber, (x) -> x + 20);
        out.println(res1);
        int res2 = modifyTheValue(myNumber, new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer t) {
                return t + 20;
            }
        });
        out.println(res2);
        /**
         * Console:
         30
         30
         30
         30
         */
    }

    /**
     * JDK8函数式接口Function、Consumer、Predicate、Supplier
     * https://blog.csdn.net/z834410038/article/details/77370785
     *
     * @param value
     * @param function1
     * @param function2
     * @return
     */
    public static Integer modifyTheValue2(int value, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        //先对value执行function1，再对value执行function2
        return function1.andThen(function2).apply(value);
    }

    @Test
    public void testAndThen() {
        out.println(modifyTheValue2(3, val -> val - 4, val -> val * 2));
        /**
         * Console:
         -2
         */
    }

    public static void modifyTheValue3(int value, Consumer<Integer> consumer) {
        consumer.accept(value);
    }

    @Test
    public void testConsumer() {
        modifyTheValue3(3, (x) -> out.println(x * 2));
        /**
         * Console:
         6
         */
    }

    public static boolean predicateTest(int value, Predicate<Integer> predicate) {
        return predicate.test(value);
    }

    @Test
    public void testPredicate() {
        out.println(predicateTest(3, (x) -> x == 3));
        /**
         * Console:
         true
         */
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                out.println(n + " ");
            }
        }
    }

    @Test
    public void testPredicate2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //输出所有大于3的数
        eval(list, (x) -> x > 3);
        out.println("------");
        //输出所有偶数
        eval(list, (x) -> x % 2 == 0);
    }

    public static boolean validInput(String name, Predicate<String> function) {
        return function.test(name);
    }

    @Test
    public void testPredicate3() {
        String name = "冷冷";
        if (validInput(name, s -> !s.isEmpty() && s.length() <= 3)) {
            out.println("名字输入正确");
        }
        /**
         * Console:
         名字输入正确
         */
    }

    public static Integer supplierTest(Supplier<Integer> supplier) {
        return supplier.get();
    }

    @Test
    public void testSupplier() {
        String name = "冷冷";
        out.println(supplierTest(() -> name.length()));
        /**
         * Console:
         2
         */
    }

}
