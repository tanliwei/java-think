package cn.tanlw.java.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static cn.tanlw.java.file.RandomAccessFileTest.*;

/**
 * @create 2018/8/8
 */
public class RandomAccessFileTest {
    //大日志文件
    public static final String filepath = "e:/a.log";
    public static final String filepath2 = "e:/a/b.log";
    public static final int BUFF_SIZE = 1 * 1024;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newScheduledThreadPool(10);

        for (int i = 0; i < 10; i++) {
            try {
                executorService.submit(new ReadPart(1024 * (i + 10))).get();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
        executorService.shutdown();
    }


}

class ReadPart implements Callable {

    private static final int SMALL_BUFF_SIZE = 200;
    private Integer start;

    public ReadPart(Integer start) {
        this.start = start;
    }

    public Object call() throws IOException {
        try {
            RandomAccessFile raf = new RandomAccessFile(filepath, "r");
            System.out.println(Thread.currentThread().getName() + "读文件");
            raf.seek(start);
            byte[] buff = new byte[BUFF_SIZE];
            int hasRead;
            int cnt = 0;
            StringBuilder sb = new StringBuilder(Thread.currentThread().getName() + ":" + new Date().getTime() + "\r\n");
            while (cnt < 2) {
                if (start>0) {
                    int newSeek = latestSlashPostion(start, raf);
                    int newSeek2 = latestSlashPostion(start+BUFF_SIZE,raf);
                    byte[] buff2 = new byte[newSeek2 - newSeek];
                    raf.seek(newSeek);
                    if ((hasRead = raf.read(buff2)) > 0) {
                        sb.append(Thread.currentThread().getName() + ":\r\n" + new String(buff2, 0, hasRead)+"\r\n");
                    }
                }
                cnt++;
            }
            sb.append(Thread.currentThread().getName() + ":" + new Date().getTime() + "\r\n");

            synchronized (RandomAccessFileTest.class) {
                RandomAccessFile targetRaf = new RandomAccessFile(filepath2, "rw");
                byte[] input = sb.toString().getBytes("UTF-8");
                targetRaf.skipBytes(new Integer(targetRaf.length() + ""));
                targetRaf.write(input);
                targetRaf.close();
                raf.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {

            e.printStackTrace();
            throw e;
        }
        return null;
    }

    /**
     * 上一个换行符的位置
     * @param start
     * @param raf
     * @return
     */
    private int latestSlashPostion(Integer start, RandomAccessFile raf) {
        if (start<=0) {
            return start;
        }
        try {
            int newStart = start - 100;
            raf.seek(newStart);

            byte[] buff = new byte[SMALL_BUFF_SIZE];
            int hasRead;
            if ((hasRead = raf.read(buff)) > 0) {
                int i = 100;
                for (; i > 0; i--) {
                    if (buff[i] == '\n') {
                     break;
                    }
                }
                return newStart + i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}