package cn.tanlw.java.util.version;

/**
 * @create 2018/7/23
 */
public class StringUtils {
    public static boolean isEmpty(String version) {
        if (version == null || version.length() == 0) {
            return true;
        }
        return false;
    }
}
