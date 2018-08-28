package cn.tanlw.interview.logCounter.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * https://www.cnblogs.com/jingzhenhua/p/5910758.html
 * -- 如何利用输入输出流往文件中不断添加内容
 *
 * java读写文件，读超大文件
 * -- https://blog.csdn.net/jiesa/article/details/51415769
 *
 * @create 2018/8/7
 */
public class CreateAMaxSizeFile {
    private static String[] keys = new String[]{"JAVA", "C++", "C#", "ASP"};

    public static void main(String[] args) throws IOException {
        String filepath = "e:/test/";
        String filename = "a.log";
        createIfNE(new File(filepath));
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            FileOutputStream out = new FileOutputStream(filepath + filename, true);
            for (int j = 0; j < 10000; j++) {
                try {
                    String str = keys[new Random().nextInt(keys.length)] + ":" + "用新的构造方法追加内容\r\n";
                    out.write(str.getBytes());
                } catch (Exception e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
        }
    }

    private static void createIfNE(File file) {
        if (file.exists()){
        } else {
            createIfNE(file.getParentFile());
            file.mkdir();
        }
    }
}
