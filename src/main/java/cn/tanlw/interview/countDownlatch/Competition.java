package cn.tanlw.interview.countDownlatch;

import java.util.concurrent.CountDownLatch;

/**
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
            latch.countDown();
        }
        /** Console:
         Car:Thread-0 is running
         Car:Thread-5 is running
         Car:Thread-1 is running
         Car:Thread-6 is running
         Car:Thread-2 is running
         Car:Thread-9 is running
         Car:Thread-3 is running
         Car:Thread-8 is running
         Car:Thread-7 is running
         Car:Thread-4 is running
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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //出发
        System.out.println("Car:"+Thread.currentThread().getName()+ " is running");
    }
}
