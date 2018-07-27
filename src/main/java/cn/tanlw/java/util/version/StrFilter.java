package cn.tanlw.java.util.version;

/**
 * @create 2018/7/23
 */
public class StrFilter {

    public static String filterNumber(String number)
    {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    public static String filterAlphabet(String alph)
    {
        alph = alph.replaceAll("[^(A-Za-z)]", "");
        return alph;
    }
}
