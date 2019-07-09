package cn.tanlw.java8;

import org.junit.Test;

import java.util.HashMap;

/**
 * (coll) hashMap.put(hashMap, x) leads to infinite loop
 * https://bugs.openjdk.java.net/browse/JDK-4895001
 * 
 * 
 * A DESCRIPTION OF THE PROBLEM :
 * If you create a HashMap that has a value that contains a cyclic reference to itself calling hashCode() on the original HashMap will fall into an infinite loop.
 * 
 * 
 * BT2:EVALUATION
 *
 * Sorry, it is too difficult and expensive to detect cycles in recursive data structures
 * like HashMap, when the only advantage is to throw a nicer exception.
 *
 * We provide IdentityHashmap for those maps that want to be keys of themselves.
 */
public class CyclicTest {

    @Test(expected = StackOverflowError.class)
    public void test1(){
        HashMap hm = new HashMap();
        hm.put( hm, "1" );
        hm.put( hm, "2" );// StackOverflowError!!
    }
    
    @Test(expected = StackOverflowError.class)
    public void test2() {

        HashMap m1 = new HashMap();
        HashMap m2 = new HashMap();

        System.out.println("Put 1");
        m1.put("Name", "Fred");
        System.out.println("Put 2");
        m2.put("Name", "John");
        System.out.println("Put 3");
        m1.put("child", m2);
        System.out.println("Put 4");
        m2.put("Cyclic", m1);
        System.out.println("Call hashCode()");
        m2.hashCode();
        System.out.println("Finish");
    }
}
