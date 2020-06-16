package cn.tanlw.java;

import java.util.Random;

public class RandomNextIntAlgorithm {
    public static void main(String[] args) {
        int k = 0;
        while(k++ < 1000 * 1000* 1000){
            // debug 加上断点 触发 u - (r = u % bound) + m 的值越界后 为负数, < 0
            // 不直接返回 u % bound, 还要进行一次上面的运算， 意义是什么？
            int i = new Random().nextInt(30);
        }
//        System.out.println("i:"+i);
        /**
         *     public int nextInt(int bound) {
         *         if (bound <= 0)
         *             throw new IllegalArgumentException(BadBound);
         *
         *         int r = next(31);
         *         int m = bound - 1;
         *         if ((bound & m) == 0)  // i.e., bound is a power of 2
         *             r = (int)((bound * (long)r) >> 31);
         *         else {
         *             for (int u = r;
         *                  u - (r = u % bound) + m < 0;
         *                  u = next(31))
         *                 ;
         *         }
         *         return r;
         *     }
         */

//        int j = new Random().nextInt(-2);
//        System.out.println("j:"+j);
    }
}
