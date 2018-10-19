package cn.tanlw.java7;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 * 遍历获得目录下所有的文件
 *  基于java Files类和Paths类的用法(详解)
 *  Java7中文件IO发生了很大的变化，专门引入了很多新的类：
 *  https://www.jb51.net/article/129130.htm
 * @Creator Tan Liwei
 * @Date 2018/10/18 11:30
 */
public class NioPath {
    private static String directory = "D:\\nginx-1.14.0";
    public static final String filepath = "D:\\nginx-1.14.0\\conf\\nginx.conf";
    public static void main(String[] args) throws IOException {
        //遍历整个目录，包括子目录
        Path startingDir = Paths.get(directory);
        List<Path> result = new LinkedList<>();
        Files.walkFileTree(startingDir, new FindJavaVisitor(result));
        System.out.println("result.size()=" + result.size());
        /**
         * Console:
         * [目录下所有文件]
         */

        result.stream().forEach(item -> System.out.println(item.toFile().getAbsolutePath()));
    }

    private static class FindJavaVisitor extends SimpleFileVisitor {
        private List<Path> result;
        public FindJavaVisitor(List<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            result.add((Path)file);
            return FileVisitResult.CONTINUE;
        }
    }

    @Test
    public void readFile() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(filepath), StandardCharsets.UTF_8);
        String str;
        /**
         * Console:
         * [文件内容]
         */
        while ((str = reader.readLine()) != null){
            System.out.println(str);
        }
    }

    @Test
    public void writeFile() throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(filepath+"test"), StandardCharsets.UTF_8);
        writer.write("Hello NIO");
        writer.flush();
        writer.close();
    }
}
