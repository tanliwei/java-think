package cn.tanlw.java7.deletefile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liwei.tan
 * @Date 2018/11/9 18:15
 */
public class DeleteFilesByType {
    public final static String[] deletedFileTypes = new String[]{".jar",".war", ".class",".log",".zip",".tar"};
    public static void main(String[] args) throws IOException {
        String targetDicrectory = "D:\\Java\\noa";
        //目标目录
        Path targetPath = Paths.get(targetDicrectory);

        List<Path> result = new LinkedList<>();
        Files.walkFileTree(targetPath, new FileFileVisitor(result));
        for (Path oneFile :
                result) {
            System.out.print(oneFile.toString()+" ");
            boolean delete = oneFile.toFile().delete();
            System.out.println(delete);
        }
    }

    private static class FileFileVisitor extends SimpleFileVisitor {

        private final List<Path> result;

        public FileFileVisitor(List<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            Path path = (Path) file;
            if (deletedFileType(path)) {
                result.add((Path)file);
            }
            return FileVisitResult.CONTINUE;
        }

        private boolean deletedFileType(Path path) {
            String filepath = path.toString();
            return Arrays.stream(deletedFileTypes).anyMatch(item -> filepath.endsWith(item));
        }
    }
}
