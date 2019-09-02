package sanjiaotie.com.goodcooder.findLongPalindrome;

/**
 * author：yuxinfeng on 2019-09-02 18:45
 * email：yuxinfeng@corp.netease.com
 */
public class Main {

    /**
     * 暴力破解
     * @param s
     * @return
     */

    public static String longestPalindrome1(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }

    /**
     * 中心扩展
     * 思路：
     * 1. 回文肯定是对称的
     * 2. 我们以某个中心为起点，向两边扩散
     * 3. 这样的中心点数就可能有两种情况1.奇数个 n 个 偶数 n+1个，总共2n+1
     * 4. 获得扩散后最长的字符串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int start = 0,end = 0;
        if (s == null || s.length() < 1) return "";

        for(int i = 0; i < s.length(); i++) {

            int len1 = centerRound(s, i, i);
            int len2 = centerRound(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }


        return s.substring(start, end + 1);
    }

    public static int centerRound(String s, int left, int right) {
        while (left>=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right ++;
        }
        return (right - left) - 1;
    }

    public static boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }



    public static void main(String args[]) {
        String a = "babad";
        System.out.println(longestPalindrome(a));

    }

}
