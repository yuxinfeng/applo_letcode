package sanjiaotie.com.goodcooder.Nigth.src;

/**
 * Created by yuxinfeng on 17/7/6.
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0)
            return -1;
        int c[] = new int[nums1.length + nums2.length];
        int f = 0;
        int s = 0;

        for (int i = 0; i < nums1.length + nums2.length; i++) {
            if (s==nums1.length && f < nums2.length) {
                for (int k = f; k < nums2.length; k++) {
                    c[i] = nums2[k];
                    i++;
                }
                break;
            }

            if (f==nums2.length && s < nums1.length) {
                for (int k = s;  k < nums1.length; k++) {
                    c[i] = nums1[k];
                    i++;
                }
                break;
            }

            if (f == nums2.length && s == nums1.length) {
                break;
            }
            if (nums1[s] <= nums2[f]) {
                c[i] = nums1[s];
                s++;
            } else {
                c[i] = nums2[f];
                f++;
            }
        }

        int t = c.length / 2;

        if (c.length % 2 == 0) {
            return (double) (c[t - 1] + c[t]) / 2;
        } else {
            return (double) c[t];
        }
    }

    public static void main(String args[]){
        Solution solution = new Solution();
        int a[] = {1,3};
        int b[] = {2};
        System.out.print(solution.findMedianSortedArrays(a, b));
    }
}
