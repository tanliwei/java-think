package cn.tanlw.java8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

    /**
     * How can I return LocalDate.now() in milliseconds?
     * https://stackoverflow.com/questions/45813379/how-can-i-return-localdate-now-in-milliseconds?noredirect=1&lq=1
     */
    @Test
    public void test(){
        LocalDate dtNow = LocalDate.now(); // 2017-08-23
        //There is no time difference between Greenwich Mean Time and Coordinated Universal Time
        System.out.println(dtNow.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());

        System.out.println(dtNow.getMonth().toString());
    }

    @Test
    public void test2(){
        LocalDate dtNow = LocalDate.now();
        dtNow.format(DateTimeFormatter.ofPattern(""));
    }
}
