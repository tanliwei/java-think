package cn.tanlw.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class ObjectMapperTest {

    @Test
    public void test() {
        String input = "[\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"countryAbbr\": \"AF\",\n" +
                "        \"countryName\": \"Afghanistan\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"countryAbbr\": \"AL\",\n" +
                "        \"countryName\": \"Albania\"\n" +
                "    }]";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Country[] strings = objectMapper.readValue(input, Country[].class);
            Country country = Arrays.stream(strings).filter(c -> c.getId() == 8).findAny().orElse(null);
            System.out.println("country:" + country);
//            System.out.println(map.get(8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Country {
    private int id;
    private String countryAbbr;
    private String countryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryAbbr() {
        return countryAbbr;
    }

    public void setCountryAbbr(String countryAbbr) {
        this.countryAbbr = countryAbbr;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryAbbr='" + countryAbbr + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
