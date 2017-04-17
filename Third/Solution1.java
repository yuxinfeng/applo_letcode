import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yuxinfeng on 17/3/21.
 */
public class Solution1 {

    /**
     * For example, given array S = {-1 0 1 2 -1 -4},
     * A solution set is:
     * (-1, 0, 1)
     * (-1, -1, 2)
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> arrList = new ArrayList<>();
        if (null == nums || nums.length < 3) {
            return arrList;
        }
        // 升序
        Arrays.sort(nums);

        /**
         * 算法思想: 固定第一个数a, 动态计算b+c=a的情况, 考虑删除重复序列的情况
         */
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果发现和前一次的数一样,跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (j > i + 1 && nums[j] == nums[j + 1]) {
                    j++;
                    continue;
                }

                if (k < nums.length - 1 && nums[k] == nums[k - 1]) {
                    k--;
                    continue;
                }
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(nums[i]);
                    arrayList.add(nums[j]);
                    arrayList.add(nums[k]);
                    arrList.add(arrayList);
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                }
            }

        }
        return arrList;
    }

    public static void main(String []args) {
        int[] a = new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        List<List<Integer>> b = Solution1.threeSum(a);
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.get(i).size(); j++) {
                System.out.print(b.get(i).get(j));
            }
            System.out.println();
        }
    }
}
