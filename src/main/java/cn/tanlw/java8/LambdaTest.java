package cn.tanlw.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/index.html
 * 在对于一个 Stream 进行多次转换操作 (Intermediate 操作)，每次都对 Stream 的每个元素进行转换，而且是执行多次，这样时间复杂度就是 N（转换次数）个 for 循环里把所有操作都做掉的总和吗？其实不是这样的，转换操作都是 lazy 的，多个转换操作只会在 Terminal 操作的时候融合起来，一次循环完成。我们可以这样简单的理解，Stream 里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，在 Terminal 操作的时候循环 Stream 对应的集合，然后对每个元素执行所有的函数。
 * 一个 Stream 只可以使用一次.
 *
 * Stream 的特性可以归纳为：
 不是数据结构
 它没有内部存储，它只是用操作管道从 source（数据结构、数组、generator function、IO channel）抓取数据。
 它也绝不修改自己所封装的底层数据结构的数据。例如 Stream 的 filter 操作会产生一个不包含被过滤元素的新 Stream，而不是从 source 删除那些元素。
 所有 Stream 的操作必须以 lambda 表达式为参数
 不支持索引访问
 你可以请求第一个元素，但无法请求第二个，第三个，或最后一个。不过请参阅下一项。
 很容易生成数组或者 List
 惰性化
 很多 Stream 操作是向后延迟的，一直到它弄清楚了最后需要多少数据才会开始。
 Intermediate 操作永远是惰性化的。
 并行能力
 当一个 Stream 是并行化的，就不需要再写多线程代码，所有对它的操作会自动并行进行的。
 可以是无限的
 集合有固定大小，Stream 则不必。limit(n) 和 findFirst() 这类的 short-circuiting 操作可以对无限的 Stream 进行运算并很快完成。
 */
public class LambdaTest {


    @Test
    public void reduceTest() {
        String concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        /**
         * 小写字母的ASCII值大于 大写字母的ASCII值
         * Console:
         * ace
         */
        System.out.println(concat);
    }

    @Test
    public void optionalTest() {
        /**
         * Console:
         abcd

         6
         0
         -1
         */
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);
        System.out.println(getLength(strA));
        ;
        System.out.println(getLength(""));
        System.out.println(getLength(strB));
    }

    public static void print(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
//        if (text != null) {
//            System.out.println(text);
//        }
    }

    public static int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        // Pre-Java 8
// return if (text != null) ? text.length() : -1;
    }

    ;

    @Test
    public void testLimitAndSkip() {
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        //这是一个有 10，000 个元素的 Stream，但在 short-circuiting 操作 limit 和 skip 的作用下，管道中 map 操作指定的 getName() 方法的执行次数为 limit 所限定的 10 次，而最终返回结果在跳过前 3 个元素后只有后面 7 个返回。
        List<String> personList2 = persons.stream().
                map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
        System.out.println(personList2);
    }
    
    @Test
    public void testGroupingByToMap(){
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 10; i++) {
            Person person = new Person(i, "name" + i, (i%2)==0);
            persons.add(person);
        }
        Map<Boolean, List<Person>> collect = persons.stream().collect(Collectors.groupingBy(Person::isAdult));
        collect.entrySet().stream()
                .forEach(entry -> System.out.println("key:"+entry.getKey() + " " + "value:"+entry.getValue()));
        //The result of each element should be unique 
        Map<Integer, Boolean> collect2 = persons.stream().collect(Collectors.toMap(Person::getNo, Person::isAdult));
        collect2.entrySet().forEach(entry -> System.out.println("key: "+entry.getKey() + " value:"+entry.getValue()));
    }

    private class Person {
        private int age;
        public int no;
        private String name;
        public boolean adult = false;

        public Person(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public Person(int no, String name, boolean adult) {
            this.no = no;
            this.name = name;
            this.adult = adult;
        }

        public Person(int i, String s, int i1) {
            this.no = i;
            this.name = s;
            this.age = i1;
        }

        public int getNo() {
            return no;
        }

        public boolean isAdult() {
            return adult;
        }

        public String getName() {
            return name;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge(){
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", no=" + no +
                    ", name='" + name + '\'' +
                    ", adult=" + adult +
                    '}';
        }
    }

    @Test
    public void generateTest() {
        Random mySeed = new Random();
        Supplier<Integer> random = mySeed::nextInt;
        /**
         * Console:
         371796501
         -334157812
         -239663004
         20
         6
         66
         999
         993
         261
         */
        //把 Supplier 实例传递给 Stream.generate() 生成的 Stream，默认是串行（相对 parallel 而言）但无序的（相对 ordered 而言）。
        // 由于它是无限的，在管道中，必须利用 limit 之类的操作限制 Stream 大小。
        Stream.generate(random).limit(3).forEach(System.out::println);
        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(3).forEach(System.out::println);

        IntStream.generate(() -> mySeed.nextInt(1000)).limit(3).forEach(System.out::println);
    }

    @Test
    public void supplierTest(){
        Stream.generate(new PersonSupplier()).limit(10).forEach( p -> System.out.println(p.getName() + ","+p.getAge() ));
    }

    private class PersonSupplier implements Supplier<Person> {
        private int index = 0;
        private Random random = new Random();
        @Override
        public Person get() {
            return new Person(index++, "StormTestUser"+index, random.nextInt(100));
        }
    }

    @Test
    public void concatTest(){
        List<Person> classA = new ArrayList<>();
        List<Person> classB = new ArrayList<>();
        classA.add(new Person(1, "Tom"));
        classA.add(new Person(2, "Jack"));

        classB.add(new Person(3, "Bruce"));
        classB.add(new Person(4, "Eden"));

        Stream.concat(classA.stream(), classB.stream()).forEach( p -> p.setName(p.getName()+"concat"));
        /**
         * Console：
         Person{age=0, no=1, name='Tomconcat'}
         Person{age=0, no=2, name='Jackconcat'}
         */
        classA.stream().forEach(System.out::println);
        /**
         * Console:
         Person{age=0, no=3, name='Bruceconcat'}
         Person{age=0, no=4, name='Edenconcat'}
         */
        classB.stream().forEach(System.out::println);
    }

}
