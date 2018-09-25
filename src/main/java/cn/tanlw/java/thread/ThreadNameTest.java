package cn.tanlw.java.thread;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @create 2018/8/17
 */
public class ThreadNameTest {
    private static AtomicInteger count = new AtomicInteger(1);
    private static String APP_UUID = UUID.randomUUID().toString();
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
//        ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
        ExecutorService executorService = Executors.newFixedThreadPool(10, threadFactory);
        for(int i = 0; i < 100; i++){
            executorService.submit(new DemoThread());
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static class DemoThread implements Runnable {
        @Override
        public void run() {
            Thread.currentThread().setName("noa:task:"+APP_UUID+ ":"+count.getAndIncrement());
            //业务
            System.out.println(Thread.currentThread().getName());

        }
    }
}
