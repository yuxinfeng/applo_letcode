import java.util.HashMap;
/**
 * Created by yuxinfeng on 17/3/12.
 */
public class Solution {
    /**
     * @param substr
     * @return true substr是否重复
     */
//    public boolean isRepeatedSubCharacter(String substr) {
//        if (substr == null && substr.isEmpty()) {
//            return false;
//        }
//        HashMap<Character, Integer> map = new HashMap<>();
//        for (int i = 0 ; i < substr.length(); i++) {
//            if (map.get(substr.charAt(i)) == null) {
//                map.put(substr.charAt(i), 0);
//            }
//            map.put(substr.charAt(i), map.get(substr.charAt(i)) + 1);
//        }
//
//        Iterator iter = map.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            if ((Integer)(entry.getValue()) > 1) {
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     *  Given a string, find the length of the longest substring without repeating characters.
     *  Examples:
     *  Given "abcabcbb", the answer is "abc", which the length is 3.
     *  Given "bbbbb", the answer is "b", with the length of 1.
     *  Given "pwwkew", the answer is "wke", with the length of 3.
     *  Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *  @param s input string
     *  @return the length of the longest substring
     */
    /**
     * 思路1:
     * 1. 判断子串是否重复
     * 2. 遍历所有字串,找出最长子串
     * 问题在于时间复杂度太高0(n方)
     */
//    public int lengthOfLongestSubstring(String s) {
//        int max = 0;
//        if (s ==  null || s.isEmpty()) {
//            return max;
//        }
//        max = 1;
//        for (int i = 0; i < s.length(); i++) {
//            for (int j= i ; j <= s.length(); j++) {
//                if (isRepeatedSubCharacter(s.substring(i, j))) {
//                    if ((j - i) >= max) {
//                        max = (j - i) ;
//                    }
//                }
//            }
//        }
//        return max;
//    }


    /**
     * 思路2 优化思路
     * 用hashmap 存储,key 为char, value, char所在的位置
     * 用j记录下来重复子串所在的位置,每次有重复串时更新位置
     * 时间负责度0(n)
     * @param s
     * @return
     */

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        if (s ==  null || s.isEmpty()) {
            return max;
        }
        /**
         * hashMap : key 对应的值,value 记录位置
         */
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                // 更新j的位置
                j = Math.max(j, hashMap.get(s.charAt(i)) + 1);
            }
            hashMap.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    public static void main (String args[]) {
        Solution solution = new Solution();
        String testA = "aaaaaaaaaaaaaaa";
        String testB = "ac";
        String testC = "abcabcbb";
        System.out.println(solution.lengthOfLongestSubstring(testA));

    }
}
