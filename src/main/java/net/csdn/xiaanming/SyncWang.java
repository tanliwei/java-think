package net.csdn.xiaanming;

public class SyncWang {


    public void askQuestion(final String question) {
        System.out.println("this:" + this.toString());
        System.out.println("Wang.this:" + SyncWang.this);
        String rt = executeMessage(new SyncCallBack() {
            @Override
            public String solve(String result) {
                System.out.println("小王问的问题--->" + question);

                //模拟小李办自己的事情需要很长时间
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * 小李办完自己的事情之后想到了答案是2
                 */
                result = "答案是2";
                return result;

            }
        }, question);
        System.out.println(rt);
        play();
    }

    //同步回調
    private String executeMessage(SyncCallBack wang2, String question) {
        if (wang2 == null) {
            throw new RuntimeException("wang2 can not be null");
        }
        System.out.println("executeMessage");
        return wang2.solve(question);
    }

    public void play() {
        System.out.println("我要逛街去了");
    }


}
