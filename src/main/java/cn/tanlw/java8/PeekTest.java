package cn.tanlw.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;


public class PeekTest {

    /**
     * https://dzone.com/articles/peeking-inside-java-streams-with-streampeek
     * The Stream.peek(Consumer) method expects a Consumer,
     * which is essentially a block of code that accepts a single argument and returns nothing.
     * The peek(Consumer) method returns the same elements of the stream that were passed to it,
     * so there will be no changes to the contents of the stream
     * unless the block of code passed to the peek(Consumer) method mutates the objects in the stream.
     * It's likely that the vast majority of the uses of Stream.peek(Consumer)
     * are read-only printing of the contents of the objects in the stream at the time of invocation of that method.
     */
    @Test
    public void test3(){
        final List<String> strings
                = Stream.of("one","two","three","four")
                .peek(e -> out.println("Original Element:"+e))
                .filter(e -> e.length() > 3)
                .peek(e -> out.println("Filtered value:"+e))
                .map(String::toUpperCase)
                .peek(e -> out.println("Mapped value:"+e))
                .collect(Collectors.toList());
        /**
         * OUTPUT:
         Original Element:one
         Original Element:two
         Original Element:three
         Filtered value:three
         Mapped value:THREE
         Original Element:four
         Filtered value:four
         Mapped value:FOUR
         Final Results:[THREE, FOUR]
         */
        out.println("Final Results:" + strings);
    }


    @Test
    public void test4(){
        final List<String> strings
                = Stream.of("zero","one","two","three","four")
                .peek(e -> out.println("Original Element:"+e))
                .filter(e -> e.length() > 3)
                .peek(e -> out.println("Filtered value:"+e))
                .map(String::toUpperCase)
                .peek(e -> out.println("Mapped value:"+e))
                .collect(Collectors.toList());
        /**
         * OUTPUT:
         Original Element:zero
         Filtered value:zero
         Mapped value:ZERO
         Original Element:one
         Original Element:two
         Original Element:three
         Filtered value:three
         Mapped value:THREE
         Original Element:four
         Filtered value:four
         Mapped value:FOUR
         Final Results:[ZERO, THREE, FOUR]
         */
        out.println("Final Results:" + strings);
    }

    @Test
    public void test5(){
        StringBuilder sb = new StringBuilder();
        final List<String> strings
                = Stream.of("one","two","three")
                .peek(e -> e=e+"x")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        /**
         * OUTPUT:
         Final Results:[ZERO, THREE, FOUR]
         */
        out.println("Final Results:" + strings);
        out.println(sb.toString());
    }
    @Test
    public void test6(){
        StringBuilder sb = new StringBuilder();
        final List<String> strings
                = Stream.of("one","two","three")
                .peek(e -> sb.append(e + " "))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        /**
         * OUTPUT:
         Final Results:[ZERO, THREE, FOUR]
         one two three
         */
        out.println("Final Results:" + strings);
        out.println(sb.toString());
    }
    /**
     * author:https://www.geeksforgeeks.org/stream-peek-java-examples/
     */
    @Test
    public void test() {
        // Creating a list of Integers
        List<Integer> list = Arrays.asList(0, 2, 4, 6, 8);

        // Using peek without any terminal
        // operation does nothing. Hence this
        // code will produce no output.
        list.stream().peek(out::println);
    }

    @Test
    public void test2() {
        // Creating a list of Integers
        List<Integer> list = Arrays.asList(0, 2, 4, 6, 8, 10);

        // Using peek with count(), which
        // is a terminal operation
        long count = list.stream().peek(out::println).count();
        out.println("count:" + count);
    }
}
