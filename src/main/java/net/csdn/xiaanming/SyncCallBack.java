package net.csdn.xiaanming;

/**
 * 这是一个回调接口
 * @author xiaanming
 *
 */
public interface SyncCallBack {
    /**
     * 这个是小李知道答案时要调用的函数告诉小王，也就是回调函数
     * @param result 是答案
     */
    public String solve(String result);
}
