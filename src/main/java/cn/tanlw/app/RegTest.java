package cn.tanlw.app;

import org.junit.Test;

import java.util.Arrays;

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
    public void test(){

        String input = "趣头条【总部】在上海【浦东】";
        String s = input.replaceAll("【.*?】", "");
        /**
         * OUTPUT:
         * 趣头条在上海
         */
        out.println(s);
    }

    @Test
    public void testSplit(){
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
}
