package cn.tanlw.java.string;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author liwei.tan@baozun.com
 * @Date 2018/9/20 13:33
 */
public class StringCompare {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void test(){
        User u = new User();
        User u2 = new User();

        Assert.assertTrue(u.name == u2.name);

        thrown.expect(NullPointerException.class);
        System.out.println(u.name.equals(""));

        u.name = "info";
        u2.name = "warn";

        Assert.assertFalse(u.name == u2.name);

        u2.name = "info";

        Assert.assertTrue(u.name == u2.name);
    }
}


class User {
    public String name;
}