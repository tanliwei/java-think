package cn.tanlw.mat;

/**
 * 连接超时
 * jstack的使用
 * jps
 * jstack [pid]
 *
 * @author liwei.tan
 * @Date 2018/10/10 11:36
 */

public class TimeOut {
    public static void main(String[] args) {
        MockTimeOut mockTimeOut = new MockTimeOut();
        mockTimeOut.request();
    }
}


class MockTimeOut {

    public void request() {
        try {
            Thread.sleep(1000 * 60 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
