package cn.tanlw.interview.logCounter.x;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LogCounterThread10Test3 {
    public static final int SIZE = 1000 * 10000;
    private static final int THREAD_COUNT = 10;
    private static List<HashMap<String, Integer>> counterList = new ArrayList(THREAD_COUNT);
    private static Map<Integer, Object> lock = new HashMap(THREAD_COUNT);
    //    private static List contents = new ArrayList(SIZE);
    private static final CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

    static {
        init();
    }

    private static void init() {

        for (int i = 0; i < THREAD_COUNT; i++) {
//            lock.put(i, new Object());
            counterList.add(new HashMap<String, Integer>());
        }
    }

    public static void main(String[] args) {
        System.out.println("start.....");
        long beginTime = System.currentTimeMillis();
        ExecutorService es = Executors.newCachedThreadPool();
//        int page = contents.size() / THREAD_COUNT;
        for (int i = 0; i < THREAD_COUNT; i++) {
            es.submit(new CountWord(i));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        //SIZE: 1000* 100  : 183
        //SIZE: 1000* 10000  : 4810, 4778
        //addWork SIZE: 1000* 100000 : 33644, 33904
        //addWork SIZE + init(): 24040
        System.out.println("耗时:" + (endTime - beginTime));
        System.out.println("result:" + getResult(counterList, "Hello"));
        //todo
        es.shutdown();
    }

    //遍历所有 计数器 获取总数
    private static Integer getResult(List<HashMap<String, Integer>> counterList, String target) {
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


    static class CountWord implements Runnable {

        private int end;
        private int start;
        private int index;
        private List contents = new ArrayList(SIZE);

        public CountWord(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("run()...");
            init();
            long beginTime = System.currentTimeMillis();
            try {
                System.out.println(Thread.currentThread().getName() );
                for (int i = 0; i < SIZE; i++) {
                    String content = contents.get(i) + "";
                    String hit = content.split(":")[0];
                    countOneLine(hit, index);

                }
            } finally {
                latch.countDown();
            }

            long endTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "耗时:" + (endTime - beginTime));
        }

        private void init() {
            for (int i = 0; i < SIZE; i++) {

                contents.add("Hello:Saturday");
                contents.add("OK:Morning");
                contents.add("Bye:Yesterday");
                contents.add("Welcome:Tomorrow");
                contents.add("Well:Today");
            }
        }

        //统计这行日志的错误类型
        public void countOneLine(String type, int index) {
            WorkTime.addWork();
            HashMap<String, Integer> HashMap = counterList.get(index);
            if (HashMap.get(type) == null) {
                HashMap.put(type, 1);
                return;
            }
            HashMap.put(type, HashMap.get(type) + 1);
        }
    }
}

