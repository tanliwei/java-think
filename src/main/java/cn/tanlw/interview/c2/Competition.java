package cn.tanlw.interview.c2;

import java.util.concurrent.CountDownLatch;

/**
 * 参考：
 * Java CountDownLatch使用实例
 * 选手+裁判
 * https://blog.csdn.net/zqyoncemore/article/details/79565680
 *
 * n个赛车，同时出发
 * @Creator Tan Liwei
 * @Date 2018/10/8 20:12
 */
public class Competition {

    public static void main(String[] args) {
        int n = 10;
        //令牌抢
        CountDownLatch latch = new CountDownLatch(n);



        for (int i = 0; i < 10; i++) {
            Car car = new Car(latch);
            new Thread(car).start();
        }
        try {
            latch.await();
            System.out.println("Running...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /** Console:
         Car:Thread-4 is ready
         Car:Thread-3 is ready
         Car:Thread-5 is ready
         Car:Thread-9 is ready
         Car:Thread-1 is ready
         Car:Thread-2 is ready
         Car:Thread-0 is ready
         Car:Thread-6 is ready
         Car:Thread-8 is ready
         Car:Thread-7 is ready
         Running...
         *
         */
    }
}

/**
 * 赛车
 */
class Car implements Runnable{
    private final CountDownLatch latch;

    public Car(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        latch.countDown();
        //就绪
        System.out.println("Car:"+Thread.currentThread().getName()+ " is ready");
    }
}
