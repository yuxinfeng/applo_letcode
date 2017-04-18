/**
 * Created by yuxinfeng on 17/3/22.
 */
public class Solution2 {
    public static int strStr(String haystack, String needle) {
        int a = haystack.indexOf(needle);
        return a;
    }

    public static void main(String []args) {
        Solution2.strStr("aabccdad", "cd");
    }
}
