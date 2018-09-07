package cn.tanlw.java.util.snowflake;

import org.junit.Test;

/**
 * @author liwei.tan@baozun.com
 * @Date 2018/9/4 17:08
 */
public class BitOperation {


    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private static long datacenterId = 1;  //数据中心
    private static long machineId = 2;     //机器标识
    private static long sequence = 0L; //序列号
    private static long lastStmp = -1L;//上一次时间戳

    public static void main(String[] args) {
        //-1L ^ (-1L << DATACENTER_BIT);
        toBinaryStringOut(-1L);
        toBinaryStringOut(-1L << DATACENTER_BIT);
        toBinaryStringOut(-1L ^ (-1L << DATACENTER_BIT));

        long currStmp = System.currentTimeMillis();
        long result = (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分

        System.out.println("currStmp:"+currStmp);
        String x5 = Long.toBinaryString(currStmp);
        System.out.println(x5+":currStmp:"+x5.length());
        long x3 = (currStmp - START_STMP) << TIMESTMP_LEFT;
        toBinaryStringOut(x3);

    }

    @Test
    public void TestOr(){
        long currStmp = System.currentTimeMillis();
        toBinaryStringOut(currStmp);
        long deta = currStmp - START_STMP;
        toBinaryStringOut(deta);
        long detaLeft = deta << TIMESTMP_LEFT;
        toBinaryStringOut(detaLeft);
        long dataCenterLeft = datacenterId << DATACENTER_LEFT;
        toBinaryStringOut(dataCenterLeft);
        long l = detaLeft | dataCenterLeft;
        toBinaryStringOut(l);
    }

    private static void toBinaryStringOut(long currStmp) {
        String x = Long.toBinaryString(currStmp);
        System.out.println(x+":"+x.length());
    }

    @Test
    public void testX(){
        long x = 56149739433L;
        toBinaryStringOut(x);
        long l = x << TIMESTMP_LEFT;
        toBinaryStringOut(l);
    }
}
