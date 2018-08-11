package cn.tanlw.interview.logCounter;//评测题目: 应用日志分析

import cn.tanlw.interview.imports.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 日志文件 /home/admin/kk.log , 每行都是一行错误日志
 READE: path not find error
 WRITE: flush error
 LISTEN: NO SOUND
 SAY: 123123123123123
 每行冒号前的字段是错误类型

 需求是， 我输入一个错误类型， 比如WRITE , 返回出现了几次

 内存溢出、大文件、
 定时任务、滥用、老任务占用资源、RandomAccess skip方法、 start  end 每次进入 start 都从0开始、统计result
 Java 内存映射

 **/

//省略imports
public class LogCounterQA2 {

    //计数器
    //private static Map<String, Integer> counter = new HashMap();
    //计数器列表
    private static List<Map<String, Integer>> counterList = new ArrayList<>();
    //latestLine上次统计到哪一行
    private static List<Integer> latestLineList = new ArrayList();

    private static final String filepath = "/home/admin/kk.log";

    static{
        //初始化
        for(int i = 0; i < 11; i++){
            counterList.add( new HashMap<String, Integer>());
            latestLineList.add(new Integer("0"));
        }
    }

    //上一次统计到第几行
    private static volatile Integer startLine = new Integer("0");

    static {
        //定时线程， 每5分钟统计一次
        //开启 11 个线程 分段统计 日志 关键字个数
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(11);
        int i = 0;
        for(; i < 10; i++){
            schedule.scheduleAtFixedRate(new CountWords(1000*10*i, 1000*10*(i+1), counterList.get(i), latestLineList.get(i)), 0, 60* 5, TimeUnit.SECONDS);
        }
        //最后一个线程扫尾
        schedule.scheduleAtFixedRate(new CountWords(1000*10*10, Integer.MAX_VALUE, counterList.get(i), latestLineList.get(i)), 0, 60* 5, TimeUnit.SECONDS);
    }
    public static void main(String []args){
        //举例 WRITE
        String target = "WRITE";
        //输出结果
        Integer result = getResult(counterList, target);
        System.out.println(result);

    }

    //遍历所有 计数器 获取总数
    private static Integer  getResult(List<Map<String, Integer>> counterList, String target){
        Integer sum = 0;
        Integer sub;
        for(int i = 0; i < counterList.size(); i++){
            sub = counterList.get(i).get(target);
            if(sub == null){
                sub = 0;
            }
            sum += sub;
        }
        return sum;
    }

    static class CountWords implements Runnable {
        private Integer start;
        private Integer end;
        private Map<String, Integer> counter;
        private Integer latestLine;
        public CountWords(Integer start, Integer end, Map<String, Integer> counter, Integer latestLine ){
            this.start = start;
            this.end = end;
            this.counter = counter;
            this.latestLine = latestLine;
        }
        public void run(){
            List<String> content = getInput(filepath, start, end, latestLine);
            if(CollectionUtils.isEmpty(content)){
                return;
            }
            for(int i = 0; i < content.size(); i++){
                countOneLine(content.get(i),counter);
            }
        }
    }

    //统计这行日志的错误类型
    public static void countOneLine(String line, Map<String, Integer> counter){
        int index = line.indexOf(":");
        if(index<0)
            return;
        String type = line.substring(0,index);
        if(counter.get(type) == null){
            counter.put(type, 1);
            return;
        }
        counter.put(type, counter.get(type)+1);
    }

    //获取日志内容
    //count:当前行数
    //latestLine上次统计到哪一行
    public static List<String> getInput(String filepath, Integer start, Integer end, Integer latestLine){
        List<String> subContent = new ArrayList();
        File file = new File(filepath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line;
            Integer count = 0;
            while((line = reader.readLine()) != null){
                //从latestLine处开始统计
                if(start<= count && count <= end && count>= latestLine){
                    subContent.add(line);
                }
                count++;
            }
            //更新latestLine的值,下次读取的地方
            if(count > latestLine)
                latestLine = count;
            return subContent;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}



