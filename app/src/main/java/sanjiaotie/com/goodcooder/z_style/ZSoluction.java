package sanjiaotie.com.goodcooder.z_style;

import java.util.ArrayList;
import java.util.List;

/**
 * author：yuxinfeng on 2019-09-02 20:28
 * email：yuxinfeng@corp.netease.com
 */
public class ZSoluction {

    // 解题思路
    // 按行来存储 确定最大的行数
    // 遍历整个字符串，把字符串加入到对应的行中
    // 用一个变量godown来标记，如果第0行，从上往下行数相加，如果最后一样，curRow做递减
    public static String convert(String s, int numRows) {

        // 如果是1行，直接返回
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rowList = new ArrayList<>();
        for (int i = 0; i < Math.min(s.length() , numRows); i++) {
            rowList.add(new StringBuilder());
        }
        int curRow = 0;
        boolean godown = false;

        for (int j = 0 ; j < s.length(); j++) {
            rowList.get(curRow).append(s.charAt(j));
            if (curRow == 0 || curRow == numRows - 1) {
                godown = !godown;
            }
            curRow += godown ? 1 : -1;
        }
        String s1 = "";

        for (StringBuilder builder : rowList) {
            s1 += builder.toString();
        }
        return s1;

    }

    public static void main(String args[]) {
        String a = "abcd";
        System.out.println(ZSoluction.convert(a, 2));

    }
}
