package cn.tanlw.interview.logCounter.x;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;


public class LogCounterThread1Test2 {
    public static final int SIZE = 1000 * 10000;
    private static final int THREAD_COUNT = 10;
    private static List<ConcurrentHashMap<String, Integer>> counter = new ArrayList(10);
    private static Map<Integer, Object> lock = new HashMap();
    private static List contents = new ArrayList();
    private static final CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

    static {
        init();
    }

    public static void main(String[] args) {
        System.out.println("start.....");
        long beginTime = System.currentTimeMillis();
        int page = contents.size()/10;
        for (int i = page*8; i < page*9 + contents.size()/10; i++) {
            String content = contents.get(i) + "";
            String hit = content.split(":")[0];
            countOneLine(hit);

        }

        long endTime = System.currentTimeMillis();
        //addWork SIZE: 1000* 100000 : 3550
        System.out.println("耗时:" + (endTime - beginTime));
        System.out.println("result:" + getResult(counter, "Hello"));
    }

    //统计这行日志的错误类型
    public static void countOneLine(String type) {
        WorkTime.addWork();
        ConcurrentHashMap<String, Integer> concurrentHashMap = counter.get(0);
        if (concurrentHashMap.get(type) == null) {
            concurrentHashMap.put(type, 1);
            return;
        }
        concurrentHashMap.put(type, concurrentHashMap.get(type) + 1);

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
            counter.add(new ConcurrentHashMap<String, Integer>());
        }

    }

}

