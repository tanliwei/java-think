package cn.tanlw.java.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ByteSplit {

    public static final int LF_ASCII = 10;

    @Test
    public void test() throws IOException {
        String filepath = "d:\\downloads\\adstats_all_traffic (3).csv";
        File file = new File(filepath);
        byte[] input = Files.readAllBytes(file.toPath());
        System.out.println("input.length:"+input.length);
        byte[] bytes = substringBytes(input,LF_ASCII,6);
        System.out.println("bytes.length:"+bytes.length);
        String s = new String(bytes, "utf-8");
        System.out.println(s);
    }

    private byte[] substringBytes(byte[] input, int target, int cnt) {
        int skipLineCnt = 0;
        int i = 0;
        for (; i < input.length; i++) {
            if (target == input[i]) {
                skipLineCnt++;
            }
            if (skipLineCnt == cnt)
                break;
        }
        int start = i + 1;
        byte[] des = new byte[input.length-start];
        System.arraycopy(input, start, des, 0,
                Math.min(input.length, des.length));
        return des;
    }
}
