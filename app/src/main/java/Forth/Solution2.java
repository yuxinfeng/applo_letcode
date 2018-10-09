package Forth;

/**
 * Created by yuxinfeng on 17/3/22.
 */
public class Solution2 {
//    public static int strStr(String haystack, String needle) {
//        int a = haystack.indexOf(needle);
//        return a;
//    }


    /* *
     * p,j两个指针遍历字符串
     *
     * */
    public static int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
        }
    }

    public static void main(String []args) {
        Solution2.strStr("aabccdad", "cd");
    }
}
