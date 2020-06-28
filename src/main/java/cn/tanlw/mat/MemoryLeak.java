package cn.tanlw.mat;

/**
 * 内存泄漏堆转储 -XX:+HeapDumpOnOutOfMemoryError
 * @author liwei.tan
 * @Date 2018/10/10 11:36
 */
public class MemoryLeak {
    public static void main(String[] args) {
        while(true){
            Object o = new Object();
        }
    }
}
