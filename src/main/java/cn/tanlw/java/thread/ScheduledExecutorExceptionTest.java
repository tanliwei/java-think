package cn.tanlw.java.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 关于ScheduledExecutorService执行一段时间之后就不执行的问题
 * http://blog.163.com/scuqifuguang@126/blog/static/1713700862014728114521780/
 *  [Copyright]
 *  @author  QiFuguang
 *  Aug 25, 2014 9:48:41 PM
 */

public class ScheduledExecutorExceptionTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my:schedule:" + r.hashCode());
            }
        });
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                    int [] s = new int[1];
                System.out.println("hello");
                System.out.println(s[1]);

            }
        }, 0, 5, TimeUnit.SECONDS);

    }
}
