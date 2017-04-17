
/**
 * Created by yuxinfeng on 17/3/14.
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class Solution {

    /**
     * 求数组的下一个排序
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int temp = 0;

        // 发现最后两位 n[len - 1] < n[len]
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            temp = nums[nums.length - 1];
            nums[nums.length - 1] = nums[nums.length - 2];
            nums[nums.length - 2] = temp;
            return;
        }

        boolean flag = true;
        int poistion = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                // 记录下比前一个大的位置
                flag = false;
                poistion = i;
                break;
            }
        }

        // 例如321的形式
        if (flag) {
            inverse(nums, 0, nums.length - 1);
        } else {
            // 例如  687321 交换i-1和比num[i-1] 值;
            for (int i = poistion; i < nums.length; i++) {
                if (nums[poistion - 1] >= nums[i] && nums[i] < nums[i - 1]) {
                    // 交换 num[i], 然后对后面n个数逆序
                    temp = nums[poistion - 1];
                    nums[poistion - 1] = nums[i - 1];
                    nums[i - 1] = temp;
                    break;
                } else {
                    // 直接和最后一个数交换
                    if (i == nums.length - 1) {
                        temp = nums[poistion - 1];
                        nums[poistion - 1] = nums[i];
                        nums[i] = temp;
                    }
                }
            }
            inverse(nums, poistion, nums.length - 1);
        }
    }


    /**
     * 指定数组任意两个位置进行逆序操作
     *
     * @param arr
     * @param start 数组的起点
     * @param end   数组的终点
     */
    public static void inverse(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        for (int i = start; i <= (end - start) / 2 + start; i++) {
            int temp = arr[i];
            arr[i] = arr[end - i + start];
            arr[end - i + start] = temp;
        }
    }


    public static void main(String []args) throws Exception {
        // Solution.combination1();
        int[] num = new int[]{2, 3, 1};
        // Solution.allsort(num, "", num.length);
        Solution.nextPermutation(num);
        // Solution.inverse(num, 2, 3);
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i]);
        }

    }
}
