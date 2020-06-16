package cn.tanlw.java;

import org.junit.Test;

public class StringFormatter {
    
    
    @Test
    public void test0(){
        System.out.println(String.format("%f%%",new Double("1234.5")));
//        System.out.println(String.format("%0.2f%%",new Double("12.345")));//COMPILE ERROR
        /**
         * 2
         *
         * %w.pf
         * where
         * w -> Minimum width of total value
         * p -> Exact number of decimal after .
         * https://stackoverflow.com/questions/16873516/format-specifiers
         */
        System.out.println(String.format("%.2f%%",new Double("12.345")));
        System.out.println(String.format("%3.2f%%",new Double("12.3456")));
        System.out.println(String.format("%2.2f%%",new Double("123.45678")));
        System.out.println(String.format("%4.4f%%",new Double("123.45678")));
        System.out.println(String.format("%4.4f%%",new Double("1.2")));
        /**OUTPUT:
         * 1234.500000%
         * 12.35%
         * 12.35%
         * 123.46%
         * 123.4568%
         * 1.2000%
         */
        System.out.println("================");
        System.out.println(String.format("%3.2f%%",new Double("12")));
        System.out.println(String.format("%2.2f%%",new Double("12")));
        /**
         * OUTPUT:
         * 12.00%
         * 12.00%
         */
        System.out.println("================");
        System.out.println(String.format("%3.1f%%",new Double("0.1")));
        System.out.println(String.format("%2.1f%%",new Double("0.1")));
        /**
         * Printing 3.141592 using %f       displays 3.141592
         * Printing 3.141592 using %1.1f    displays 3.1
         * Printing 3.141592 using %1.2f    displays 3.14
         * Printing 3.141592 using %3.3f    displays 3.142
         * Printing 3.141592 using %4.4f    displays 3.1416
         * Printing 3.141592 using %4.5f    displays 3.14159
         * Printing 3.141592 using %09.3f   displays 00003.142
         * Printing 3.141592 using %-09.3f  displays 3.142
         * Printing 3.141592 using %9.3f    displays     3.142
         * Printing 3.141592 using %-9.3f   displays 3.142
         */
    }
    
    /**
     * https://www.geeksforgeeks.org/java-string-format-examples/
     */
    @Test
    public void test(){
        
        String str = "GeeksforGeeks.";

        // Concatenation of two strings 
        String gfg1 = String.format("My Company name is %s", str);

        // Output is given upto 8 decimal places 
        String str2 = String.format("My answer is %.8f", 47.65734);

        // between "My answer is" and "47.65734000" there are 15 spaces 
        String str3 = String.format("My answer is %15.8f", 47.65734);
        /**
         * My Company name is GeeksforGeeks.
         * My answer is 47.65734000
         * My answer is     47.65734000
         */
        System.out.println(gfg1);
        System.out.println(str2);
        System.out.println(str3);
    }
    
    @Test
    public void test2(){
        int num = 7044;

        // Output is 3 zero's("000") + "7044", 
        // in total 7 digits 
        String gfg3 = String.format("%07d", num);
        /**
         * 0007044
         */
        System.out.println(gfg3);
    }
    
    @Test
    public void test3(){
        String str1 = "GFG";
        String str2 = "GeeksforGeeks";

        //%1$ represents first argument, %2$ second argument 
        String gfg2 = String.format("My Company name" +
                " is: %1$s, %2$s and %1$s", str1, str2);
        /**
         * My Company name is: GFG, GeeksforGeeks and GFG
         */
        System.out.println(gfg2);
    }
}
