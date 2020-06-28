package cn.tanlw.mat;

/**
 * 模拟大对象, 使用MAT分析
 *
 * 列出pid: jps /tasklist | findstr "app name"
 * 输出heap.bin:  jmap -heap:format=b pid
 * 使用MAT分析 Top Consumers
 * 查看年轻代(Eden Space, From, To)和老年代(PS Old Generation)的使用情况 jmap -heap pid
 * @author liwei.tan
 * @Date 2018/10/10 10:43
 */
public class BigArray {
    public static Integer[][] bigArr = new Integer[2000 ][1000 ];
    public static void main(String[] args) {
        for (int i = 0; i < 2000 ; i++) {
            for (int j = 0; j < 1000 ; j++) {
                bigArr[i][j] = new Integer(i+j);
            }
        }
        while(true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
