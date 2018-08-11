package cn.tanlw.interview.logCounter.x;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LogCounterThread100Test {
    public static final int SIZE = 1000 * 10000;
    private static final int THREAD_COUNT = 100;
    private static List<ConcurrentHashMap<String, Integer>> counterList = new ArrayList(100);
    private static Map lock = new HashMap(THREAD_COUNT);
    private static List contents = new ArrayList(SIZE);
    private static final CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

    static {
        init();
    }

    public static void main(String[] args) {
        System.out.println("start.....");
        long beginTime = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            //先除后乘，防止数据溢出
            int pageSize = contents.size()/THREAD_COUNT;
            es.submit(new CountWord(i * pageSize, (i + 1) * pageSize, i));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        //SIZE: 1000* 100  : 210
        //SIZE: 1000* 10000 : 4816,  4944, 4791
        //AddWork SIZE: 1000* 10000  : 33786,
        System.out.println("耗时:" + (endTime - beginTime));
        System.out.println("result:" + getResult(counterList, "Hello"));
        es.shutdown();
    }

    //遍历所有 计数器 获取总数
    private static Integer getResult(List<ConcurrentHashMap<String, Integer>> counterList, String target) {
        Integer sum = 0;
        Integer sub;
        for (int i = 0; i < counterList.size(); i++) {
            sub = counterList.get(i).get(target);
            if (sub == null) {
                sub = 0;
            }
            sum += sub;
        }
        return sum;
    }

    private static void init() {
        for (int i = 0; i < SIZE; i++) {

            contents.add("Hello:Saturday");
            contents.add("OK:Morning");
            contents.add("Bye:Yesterday");
            contents.add("Welcome:Tomorrow");
            contents.add("Well:Today");
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            lock.put(i, new Object());
            counterList.add(new ConcurrentHashMap<String, Integer>());
        }

    }


    static class CountWord implements Runnable {

        private final int end;
        private int start;
        private int index;

        public CountWord(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }

        @Override
        public void run() {
            long beginTime = System.currentTimeMillis();
            try {
                System.out.println(Thread.currentThread().getName() + " start: " + start + "  end:" + end);
                for (int i = start; i < end; i++) {
                    String content = contents.get(i) + "";
                    String hit = content.split(":")[0];
                    countOneLine(hit, index);

                }
            } finally {
                latch.countDown();
            }

            long endTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "耗时:"+(endTime - beginTime));
        }

        //统计这行日志的错误类型
        public void countOneLine(String type, int index) {
            WorkTime.addWork();
//            synchronized (lock.get(index)) {
            ConcurrentHashMap<String, Integer> concurrentHashMap = counterList.get(index);
            if (concurrentHashMap.get(type) == null) {
                concurrentHashMap.put(type, 1);
                return;
            }
            concurrentHashMap.put(type, concurrentHashMap.get(type) + 1);
//            }
        }
    }
}

