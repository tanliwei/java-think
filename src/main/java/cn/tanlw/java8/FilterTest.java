package cn.tanlw.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class FilterTest {

    @Test
    public void count(){
        String[] arrs = {"abc", "bcd", "cda", "dab"};
        long a = Arrays.stream(arrs).filter(x -> x.contains("a")).count();
        /**
         * OUTPUT:
         * 3
         */
        System.out.println(a);
    }

    @Test
    public void test() {
        String[] arrs = {"abc", "bcd", "cda", "dab"};
        Arrays.stream(arrs).forEach(System.out::println);
        System.out.println("find one:");
        Optional opt = Arrays.stream(arrs).filter(arr -> {
            return arr.contains("a");
        }).findAny();
        if (opt.isPresent()) {
            //abc
            System.out.println(opt.get());
        }
        List<String> list = Arrays.stream(arrs).filter(arr -> {
            return arr.contains("a");
        }).collect(Collectors.toList());
        System.out.println("find list:");
        /**
         * Console:
         abc
         cda
         dab
         */
        list.stream().forEach(System.out::println);
    }

    /**
     * Java 8 – Convert List to Map
     * https://www.mkyong.com/java8/java-8-convert-list-to-map/
     */
    @Test
    public void toMapTest() {
        List<Hosting> list = getHostings();

        // key = id, value - websites
        Map<Integer, String> result1 = list.stream().collect(
                Collectors.toMap(Hosting::getId, Hosting::getName));

        System.out.println("Result 1 : " + result1);

        // key = name, value - websites
        Map<String, Long> result2 = list.stream().collect(
                Collectors.toMap(Hosting::getName, Hosting::getWebsites));

        System.out.println("Result 2 : " + result2);

        // Same with result1, just different syntax
        // key = id, value = name
        Map<Integer, String> result3 = list.stream().collect(
                Collectors.toMap(x -> x.getId(), x -> x.getName()));

        System.out.println("Result 3 : " + result3);
    }

    private List<Hosting> getHostings() {
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "mkyong.com", 1));
        return list;
    }

    /**
     * Java 8 – Convert List to Map
     * https://www.mkyong.com/java8/java-8-convert-list-to-map/
     */
    @Test(expected = IllegalStateException.class)
    public void testDuplicateKeyWitException() {

        List<Hosting> list = getHostings();
        list.add(new Hosting(6, "linode.com", 100000)); // new line
        // key = name, value - websites , but the key 'linode' is duplicated!?
        Map<String, Long> result1 = list.stream().collect(
                Collectors.toMap(Hosting::getName, Hosting::getWebsites)
        );
    }

    /**
     * Java 8 – Convert List to Map
     * https://www.mkyong.com/java8/java-8-convert-list-to-map/
     */
    @Test
    public void testDuplicateKey() {

        List<Hosting> list = getHostings();
        list.add(new Hosting(6, "linode.com", 100000)); // new line
        // key = name, value - websites , but the key 'linode' is duplicated!?
        Map<String, Long> result1 = list.stream().collect(
                Collectors.toMap(Hosting::getName, Hosting::getWebsites, (oldValue, newValue) -> oldValue)
        );
        System.out.println(result1);
    }
}

class Hosting {
    private int Id;
    private String name;
    private long websites;

    public Hosting(int id, String name, long websites) {
        Id = id;
        this.name = name;
        this.websites = websites;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWebsites() {
        return websites;
    }

    public void setWebsites(long websites) {
        this.websites = websites;
    }
}

