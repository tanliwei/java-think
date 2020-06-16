package cn.tanlw.adjuster;

import org.apache.commons.httpclient.Header;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Headers {
    @Test
    public void test1(){
        List<Header> headers = fakeFireFoxHeaders();
    }
    public static String FIREFOX_AGENT = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.1.9) Gecko/20100330 Fedora/3.5.9-2.fc12 Firefox/3.5.9";
    public static List<Header> fakeFireFoxHeaders()
    {
        List<Header> headers = new ArrayList<Header>();
        //headers.add(new Header("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1.5) Gecko/20070718 Fedora/2.0.0.5-1.fc7 Firefox/2.0.0.5"));
        headers.add(new Header("User-Agent", FIREFOX_AGENT));
        headers.add(new Header("Accept", "text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"));
        headers.add(new Header("Accept-Language", "en-us,en;q=0.5"));
        headers.add(new Header("Accept-Encoding", "gzip,deflate"));
        headers.add(new Header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7"));
        headers.add(new Header("Keep-Alive", "400"));
        headers.add(new Header("Connection", "keep-alive"));
        return headers;
    }
}
