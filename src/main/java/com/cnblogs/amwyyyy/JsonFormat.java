package com.cnblogs.amwyyyy;

/**
 * json字符串格式化
 * https://www.cnblogs.com/amwyyyy/p/8603735.html
 *
 * @Creator Tan Liwei
 * @Date 2018/10/9 16:37
 */
public class JsonFormat {
    private static String NEW_LINE = "\r\n";

    public static void main(String[] args) {
        String json = "[{\"name\": \"wen\",\"age\": 12,\"flag\": true,\"job\": [{\"name\":\"java\"},{\"name\": \"c++\"}]},{\"name\": \"yun\",\"age\": 13,\"flag\": false}]";

        System.out.println(format(json));
    }

    private static String format(String json) {
        // 去除原来的格式
        json = json.replace("\n", "").replace("\r", "").replace("\t", "");

        StringBuilder sb = new StringBuilder();
        int prevStatus = 0;// 上一状态
        int level = 0;// 缩进层级

        for (char c : json.toCharArray()) {
            int oper = getOperation(prevStatus, c);
            switch (oper) {
                case 1:
                    sb.append(NEW_LINE).append(getTab(level));
                    break;
                case 2:
                    level++;
                    sb.append(NEW_LINE).append(getTab(level));
                    break;
                case 3:
                    level--;
                    sb.append(NEW_LINE).append(getTab(level));
                    break;
                case 4:
                    sb.append(' ');
                    break;
            }
            sb.append(c);
            prevStatus = getStatus(c);
        }

        return sb.toString();
    }

    /**
     * 返回：0直接输出，1换行缩进不变，2增加缩进并换行，3减少缩进并换行，4前面加空格
     * 行号: 上一个字符状态值{@link #getStatus(char)}
     * 列号: 字符状态值 + 1
     * 状态迁移表
     */
    private static int[][] statusArr = new int[][]{
            {0, 0, 0, 0, 0, 3},  // 普通字符
            {1, 2, 2, 0, 0, 0},  // {[
            {2, 0, 4, 0, 0, 0},  // :
            {3, 1, 1, 0, 0, 0},  // ,
            {4, 0, 0, 0, 0, 3},};// }]

    // 根据前一状态和当前字符决定操作
    private static int getOperation(int status, char c) {
        return statusArr[status][getStatus(c) + 1];
    }

    // 字符转换成对应状态
    private static int getStatus(char c) {
        int status = 0;//常规字符
        switch (c) {
            case '{':
            case '[':
                status = 1;
                break;
            case ':':
                status = 2;
                break;
            case ',':
                status = 3;
                break;
            case '}':
            case ']':
                status = 4;
                break;
        }

        return status;
    }

    // 缩进
    private static String getTab(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }
}
