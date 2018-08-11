package cn.tanlw.interview.logCounter;//评测题目: 应用日志分析

import cn.tanlw.interview.imports.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 日志文件 /home/admin/kk.log , 每行都是一行错误日志
 READE: path not find error
 WRITE: flush error
 LISTEN: NO SOUND
 SAY: 123123123123123
 每行冒号前的字段是错误类型

 需求是， 我输入一个错误类型， 比如WRITE , 返回出现了几次
 **/

//省略imports
public class LogCounterQA1 {

    //计数器
    private static Map<String, Integer> counter = new HashMap();

    //上一次统计到第几行
    private static Integer startLine = new Integer("0");

    public static void main(String []args){
        //举例 WRITE
        String target = "WRITE";

        String filepath = "/home/admin/kk.log";
        //同步
        synchronized(LogCounter.class){
            List<String> content = getInput(filepath);
            if(CollectionUtils.isEmpty(content)){
                return;
            }
            for(int i = 0; i < content.size(); i++){
                countOneLine(content.get(i));
            }
        }
        //输出结果
        System.out.println(counter.get(target) == null ? 0 : counter.get(target));

    }

    //统计这行日志的错误类型
    public static void countOneLine(String line){
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
    public static List<String> getInput(String filepath){
        List<String> subContent = new ArrayList();
//        File file = new File(filepath);
//        BufferedReader reader = new BufferedReader(file);
//        String line;
//        Integer count = 0;//当前行数
//        while((line = reader.readLine()) != null){
//            //从startLine处开始统计
//            if(count>= startLine){
//                subContent.add(line);
//            }
//            count++;
//        }
//        //更新startLine的值,下次读取的地方
//        startLine = count;
        return subContent;
    }

}



