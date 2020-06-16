package cn.tanlw.app;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
 */
public class RegTest {

    public static final String QUOTE_COMMA_CSV = "(?x)   " +
            ",          " +   // Split on comma
            "(?=        " +   // Followed by
            "  (?:      " +   // Start a non-capture group
            "    [^\"]* " +   // 0 or more non-quote characters
            "    \"     " +   // 1 quote
            "    [^\"]* " +   // 0 or more non-quote characters
            "    \"     " +   // 1 quote
            "  )*       " +   // 0 or more repetition of non-capture group (multiple of 2 quotes will be even)
            "  [^\"]*   " +   // Finally 0 or more non-quotes
            "  $        " +   // Till the end  (This is necessary, else every comma will satisfy the condition)
            ")          ";    // End look-ahead

    @Test
    public void test() {

        String input = "趣头条【总部】在上海【浦东】";
        String s = input.replaceAll("【.*?】", "");
        /**
         * OUTPUT:
         * 趣头条在上海
         */
        out.println(s);
    }

    @Test
    public void testSplit() {
        String input = "Traffic,Tag ID,Tag Name,Tag Dimensions";
        String[] arr = input.split(",");
        /** OUTPUT:
         Traffic
         Tag ID
         Tag Name
         Tag Dimensions
         */
        Arrays.stream(arr).forEach(item -> out.println(item));

        input = "Traffic,Tag ID,\"Tag ,Name\",Tag Dimensions";
        arr = input.split(QUOTE_COMMA_CSV);
        /**
         * OUTPUT:
         Traffic
         Tag ID
         "Tag ,Name"
         Tag Dimensions
         */
        Arrays.stream(arr).forEach(item -> out.println(item));
        /**
         * OUTPUT:
         Traffic
         Tag ID
         "Tag ,Name,Tag Dimensions"
         */
        input = "Traffic,Tag ID,\"Tag ,Name,Tag Dimensions\"";
        arr = input.split(QUOTE_COMMA_CSV);
        Arrays.stream(arr).forEach(item -> out.println(item));
    }

    @Test
    public void testMatchOnce() {
        String[] input = {"fdsf3243Mookieafds.csv", 
                "dfdfdsfdsfsd", 
                "fdfsdffdsf", 
                "desfdsfds", 
                "drfre43545"};
        Pattern pattern = Pattern.compile("(?!fds.*).*");
        for (String inp :
                input) {
            Matcher matcher = pattern.matcher(inp);
            System.out.println("inp:"+inp);
            System.out.println("mtc:"+matcher.matches());
            if(matcher.find()){
                System.out.println("fnd:"+matcher.group(0));
            }
        }
/**OUTPUT:
         inp:fdsf3243Mookieafds.csv
         mtc:false
         fnd:
         inp:dfdfdsfdsfsd
         mtc:true
         fnd:
         inp:fdfsdffdsf
         mtc:true
         fnd:
         inp:desfdsfds
         mtc:true
         fnd:
         inp:drfre43545
         mtc:true
         fnd:
**/
    }
}
