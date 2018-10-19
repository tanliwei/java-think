package cn.tanlw.java.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liwei.tan
 * @Date 2018/9/3 17:54
 */
//@lombok.extern.slf4j.Slf4j
public class StringHelper {


    public static boolean isIPv4(String host){
        if (org.springframework.util.StringUtils.isEmpty(host)) {
            return false;
        }
        String[] four = host.split("\\.");
        if (four == null || four.length!= 4) {
            return false;
        }
        for (int i = 0; i < four.length; i++) {
            if (!isNumber(four[i])) {
                return false;
            }
            Integer num = new Integer(four[i]);
            if (i == 0) {
                if (num < 1 || num > 255) {
                    return false;
                }
            } else {
                if (num < 0 || num > 255) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断一个字符串是否是数字。
     *
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        if (StringUtils.isEmpty(string))
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }

    /**
     * 去掉最后一个正斜杠
     * @param string
     * @return
     */
    public static String deleteLastSlash(String string){
        if (StringUtils.isEmpty(string)) {
            return string;
        }
        if (string.charAt(string.length() - 1) == '/') {
            return string.substring(0, string.length()-1);
        }
        return string;
    }

    public static URL checkUrl(String urlStr) {
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("URL不合法:" + urlStr);
        }
        return url;
    }

    /**
     * 检查内容长度
     * @param content
     * @param name
     * @param limit
     */
    public static void checkLength(String content,String name, int limit) {
        if (!StringUtils.isEmpty(content) && content.length() > limit) {
            throw new IllegalArgumentException(name + "长度超长" + " 长度限制:"+limit);
        }
    }

    public static void checkEmail(String email){
        /**
         *   ^匹配输入字符串的开始位置
         *   $结束的位置
         *   \转义字符 eg:\. 匹配一个. 字符  不是任意字符 ，转义之后让他失去原有的功能
         *   \t制表符
         *   \n换行符
         *   \\w匹配字符串  eg:\w不能匹配 因为转义了
         *   \w匹配包括字母数字下划线的任何单词字符
         *   \s包括空格制表符换行符
         *   *匹配前面的子表达式任意次
         *   .小数点可以匹配任意字符
         *   +表达式至少出现一次
         *   ?表达式0次或者1次
         *   {10}重复10次
         *   {1,3}至少1-3次
         *   {0,5}最多5次
         *   {0,}至少0次 不出现或者出现任意次都可以 可以用*号代替
         *   {1,}至少1次  一般用+来代替
         *   []自定义集合     eg:[abcd]  abcd集合里任意字符
         *   [^abc]取非 除abc以外的任意字符
         *   |  将两个匹配条件进行逻辑“或”（Or）运算
         *   [1-9] 1到9 省略123456789
         *    邮箱匹配 eg: ^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\.){1,3}[a-zA-z\-]{1,}$
         *
         */
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式 编译正则表达式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(email);
        //进行正则匹配\
        if (!m.matches()) {
            throw new IllegalArgumentException("不合法的Email:" + email);
        }
    }
}

