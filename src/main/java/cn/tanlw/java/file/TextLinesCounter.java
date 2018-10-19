package cn.tanlw.java.file;

import java.io.*;

/**
 * @create 2018/7/10
 */
public class TextLinesCounter {
    public static void main(String[] args) {
        String input = "a:\r  b: 1\n  c: 2";


        try {
            InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(input.getBytes("UTF-8")));
            BufferedReader br = new BufferedReader(reader);
            String line;
            /**Console:
             a:
               b: 1
               c: 2
             */
            while( (line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
