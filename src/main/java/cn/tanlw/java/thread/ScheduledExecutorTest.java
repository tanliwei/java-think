package cn.tanlw.java.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @create 2018/8/6
 */
public class ScheduledExecutorTest {
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
                try {
                    System.out.println("Start");
                    System.out.println(Thread.currentThread().getName() + ":"+System.currentTimeMillis());
                    Thread.sleep(1*1000);
                    //用下面这行替换, 会在11秒后 再调度
//                    Thread.sleep(11*1000);
                    System.out.println(Thread.currentThread().getName() + ":"+System.currentTimeMillis());
                    System.out.println("End");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5, TimeUnit.SECONDS);

    }
}
