package Sixth;

import java.util.HashMap;

/**
 * Created by yuxinfeng on 17/4/19.
 */
public class SolutionSixTh {

    /**
     * 用HashMap keyValue
     * @param nums
     * @param target
     * @return
     */

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int []a = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                a[1] = i;
                a[0] = map.get(target - nums[i]);
                return a;
            }
            map.put(nums[i], i);
        }
        return a;
    }

    public static void main(String[] args) {
        int []a = {3, 2, 4};
        System.out.println(twoSum(a, 6)[0]);
    }
}
