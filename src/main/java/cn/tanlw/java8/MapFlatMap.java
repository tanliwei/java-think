package cn.tanlw.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Difference Between map() and flatMap()
 * https://www.baeldung.com/java-difference-map-and-flatmap
 */
public class MapFlatMap {

    @Test
    public void test1(){
        Optional<String> s = Optional.of("test");
        Assert.assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));
    }

    /**
     * in more complex cases we might be given a function that returns an Optional too.
     * In such cases using map() would lead to a nested structure,
     * as the map() implementation does an additional wrapping internally.
     */
    @Test
    public void test2(){
        Assert.assertEquals(Optional.of(Optional.of("STRING")),
                Optional
                        .of("string")
                        .map(s -> Optional.of("STRING")));
        Assert.assertEquals(Optional.of(Optional.of("STRING")),
                Optional.of("a").map(s -> Optional.of("STRING")));
        Assert.assertNotEquals(Optional.of(Optional.of("STRING")),
                Optional.of("a").map(s -> Optional.of("STRING111")));
    }

    @Test
    public void flatMapTest(){
        Assert.assertEquals(Optional.of("String"), Optional.of("String").flatMap(s -> Optional.of("String")));
    }

    @Test
    public void testListMap(){
        List<List<String>> list = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));
        /**
         * Console:
         * [[a], [b]]
         */
        System.out.println(list);

        /**
         * Console:
         * [a, b]
         */
        System.out.println(list
        .stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList()));
    }
}
