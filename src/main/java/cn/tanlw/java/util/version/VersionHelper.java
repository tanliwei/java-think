package cn.tanlw.java.util.version;

/**
 * @create 2018/7/23
 */
public class VersionHelper {

    private final static int DEFAULT_LENGTH = 3;
    //字符串版本号, 在前面的权重小
    private final static String[] DICTIONARY = new String[]{"SNAPSHOT", "ALPHA", "BETA", "RELEASE"};

    /**
     * 正例：
     * 1.0.0
     * 1.0.2.RELEASE
     * 2.0.0.BETA2
     * 2.0.0.beta2
     * 版本号转化为数字
     *
     * @param version
     * @return
     */
    @Deprecated
    public static int transformVersion(String version) {
        if (StringUtils.isEmpty(version)) {
            return 0;
        }
        String[] bits = version.split(".");
        int versionTotalValue = 0;
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            versionTotalValue += versionTotalValue * 10 + new Integer(bits[i]);
        }
        //前三位放大1000倍
        versionTotalValue = versionTotalValue * 1000;
        if (bits.length == DEFAULT_LENGTH) {
            return versionTotalValue;
        }
        return versionTotalValue;
    }

    /**
     * 比较版本
     * 正例：
     * 1.0.0
     * 1.0.2.RELEASE
     * 2.0.0.BETA2
     * 2.0.0.beta2
     * @param oneVersion
     * @param otherVersion
     * @return
     */
    public static int compareVersion(String oneVersion, String otherVersion) {
        //处理版本为空的情况
        if (StringUtils.isEmpty(oneVersion)) {
            return StringUtils.isEmpty(otherVersion) ? 0 : -1;
        }
        if (StringUtils.isEmpty(otherVersion)) {
            return 1;
        }
        String[] bitsOne = oneVersion.split("[.]");
        String[] bitsOther = otherVersion.split("[.]");
        Integer bitOne;
        Integer bitOther;
        for (int i = 0; i < 3; i++){
            bitOne = new Integer(bitsOne[i]);
            bitOther = new Integer(bitsOther[i]);
            int result = bitOne.compareTo(bitOther);
            if (result != 0) {
                return result;
            }
        }
        //处理没有第四位的情况
        if (bitsOne.length == 3) {
            return bitsOther.length == 3 ? 0 : -1;
        }
        if (bitsOther.length == 3) {
            return 1;
        }
        String fourthBitOneStr = StrFilter.filterAlphabet(bitsOne[3]);
        String fourthBitOtherStr = StrFilter.filterAlphabet(bitsOther[3]);
        int result = getWeight(fourthBitOneStr) - getWeight(fourthBitOtherStr);
        if (result != 0) {
            return result;
        }
        return getNumber(bitsOne[3]) - getNumber(bitsOther[3]);
    }

    private static int getNumber(String fourthBit) {
        String number = StrFilter.filterNumber(fourthBit);
        if (StringUtils.isEmpty(number)) {
            return 0;
        }
        return new Integer(number);
    }

    private static int getWeight(String fourthBit) {
        for (int i = 0; i < DICTIONARY.length; i++) {
            if (fourthBit.toUpperCase().equals(DICTIONARY[i])) {
                return i;
            }
        }
        throw new RuntimeException("不支持的版本字符串:"+fourthBit);
    }

    /**
     * 比较版本
     * 正例：
     * 1.0.0
     * 1.0.2.RELEASE
     * 2.0.0.BETA2
     * 2.0.0.beta2
     * @return
     */
    public static void main(String[] args) {
        System.out.println(compareVersion("",""));
        System.out.println(compareVersion("1.0.0","1.0.0"));
        System.out.println(compareVersion("1.0.1","1.0.0"));
        System.out.println(compareVersion("1.0.0","1.0.1"));
        System.out.println("==============1");
        System.out.println(compareVersion("1.0.2.RELEASE","1.0.2.RELEASE"));
        System.out.println(compareVersion("1.0.2","1.0.2.RELEASE"));
        System.out.println(compareVersion("1.0.2.RELEASE","1.0.2"));
        System.out.println("==============2");
        System.out.println(compareVersion("2.0.0.beta2","2.0.0.beta2"));
        System.out.println(compareVersion("2.0.0","2.0.0.beta2"));
        System.out.println(compareVersion("2.0.0.beta2","2.0.0"));
        System.out.println(compareVersion("2.0.0.bEta2","2.0.0"));
        System.out.println("==============3");
        System.out.println(compareVersion("1.0.2.SNAPSHOT","1.0.2.RELEASE"));
        System.out.println(compareVersion("1.0.2.RELEASE","1.0.2.SNAPSHOT"));
    }
}
