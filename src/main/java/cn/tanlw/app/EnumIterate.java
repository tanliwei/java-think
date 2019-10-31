package cn.tanlw.app;


import java.util.Arrays;

public class EnumIterate {

    public static final DemoEnum[] VALUES =
            DemoEnum.values();

    public static void main(String[] args) {
        outputOneType("1");
    }

    private static void outputOneType(String type) {
        System.out.println(type+":");
        Arrays.stream(VALUES)
                .filter(d->d.getType().equals(type))
                .forEach(item -> System.out.println(item.getRequestName()+","));
    }

    private enum DemoEnum {
        LAPTOP("1", "laptop"),
        IPAD("2", "ipad"),
        ;
        private final String requestName;
        private final String type;

        private DemoEnum(String type, String requestName){
            this.type = type;
            this.requestName = requestName;
        }
        ;

        public String getRequestName() {
            return requestName;
        }

        public String getType() {
            return type;
        }
    }
}

