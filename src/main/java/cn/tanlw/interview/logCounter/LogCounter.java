package cn.tanlw.interview.logCounter;


import cn.tanlw.interview.imports.StringUtils;

import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

public class LogCounter {

    //计数器
    private static Map<String, Integer> counter = new HashMap();

    public static void main(String []args){
        //举例 WRITE
        String target = "WRITE";
        String filepath = "/home/admin/kk.log";
        String[] content = getInput(filepath);
        if(StringUtils.isEmpty(content)){
            return;
        }
        for(int i = 0; i < content.length; i++){
            countOneLine(content[i]);
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
    public static String[] getInput(String filePath){
//        LineNumberReader l = new LineNumberReader();
        //Apache 工具类
//        return FileUtils.readLines(filePath);
        return (String[])null;
    }
}
