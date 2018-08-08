package cn.tanlw.java.think;

/**
 * @create 2018/8/8
 */
public class TryFinallyTest {
    public static void main(String[] args) {
        /**
         * Console:
         Go go go 0
         Come on 0
         Go go go 1
         Come on 1
         Bye bye 2
         Come on 2
         Well done.
         */
        for (int i = 0; i < 4; i++) {
            try{
                if ( i < 2) {
                    System.out.println("Go go go " + i);
                    continue;
                }
                try{
                    int result = i / (i - 2);
                } catch (Exception e){
                    System.out.println("Bye bye " + i);
                    break;
                }
            } finally {
                System.out.println("Come on " + i);
            }
        }
        System.out.println("Well done.");
    }
}
