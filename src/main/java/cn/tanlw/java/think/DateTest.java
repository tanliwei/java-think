package cn.tanlw.java.think;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @create 2018/8/24
 */
public class DateTest {

    @Test
    public void dateTime(){
        Date date = new Date();
        System.out.println(date.getTime());
    }

    @Test
    public void dateTimeCompare(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        try {
            Date date1 = sdf.parse("2018/8/24 17:40:00");
            Date date2 = sdf.parse("2018/8/24 17:41:00");
            Assert.assertEquals(60 * 1000, date2.getTime() - date1.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
