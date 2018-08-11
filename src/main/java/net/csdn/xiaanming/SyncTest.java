package net.csdn.xiaanming;


/**
 * 一个经典例子让你彻彻底底理解java回调机制
 * https://blog.csdn.net/xiaanming/article/details/8703708/
 * 测试类
 * @author xiaanming
 *
 */
public class SyncTest {
    public static void main(String[]args){
        /**
         * new 一个小王
         */
        SyncWang wang = new SyncWang();

        /**
         * 小王问小李问题
         */
        wang.askQuestion("1 + 1 = ?");
    }
}


