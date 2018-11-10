package cn.tanlw.java5.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Creator Java注解基本原理 https://www.cnblogs.com/huajiezh/p/5263849.html
 * @Date 2018/11/4 21:01
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> userCases = new ArrayList<>();
        Collections.addAll(userCases, 47, 48, 49, 50);
        trackUserCases(userCases, PasswordUtils.class);

    }

    private static void trackUserCases(List<Integer> userCases, Class<PasswordUtils> passwordUtilsClass) {
        for (Method m : passwordUtilsClass.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found use case:" + uc.id() + uc.description());
                userCases.remove(new Integer(uc.id()));
            }
        }
        for (int i :
                userCases) {
            System.out.println("Warning: Missing use case - "+i);
        }
    }
}
