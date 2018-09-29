package cn.tanlw.java.extend.subPackage;

import cn.tanlw.java.extend.Parent;

public class SonB extends Parent {
    private void privateMethod(){
        System.out.println("privateMethod");
    }

    protected void sonProtectedMethod(){
        protedtedMethod();
    }
}
