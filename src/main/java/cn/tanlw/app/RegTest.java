package cn.tanlw.app;

import org.junit.Test;

public class RegTest {
    @Test
    public void test(){

        String input = "趣头条【总部】在上海【浦东】";
        String s = input.replaceAll("【.*?】", "");
        System.out.println(s);
    }
}
