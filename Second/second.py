# -*- coding: utf-8 -*-
################################################################################
#
# Copyright (c) 2016 Baidu.com, Inc. All Rights Reserved
#
################################################################################

"""
Authors: zhangxiao11(zhangxiao11@baidu.com)
Date:    2017-03-14
"""

class Solution(object):
    """Soultion
    """

    def nextPermutation(self, nums):
        """nextPermntation
        Args:
            nums: The list to find the lexicographically next greater permutation of numbers

        Returns:
            None

        Method：
        从右向左进行比较，
        (1)如果后一个数大于前一个数，则需要交换
            a.末尾两值交换，则直接swap，不需要逆转    eg[1,2,3]
            b.非末尾两值交换，则需寻找需要交换值的合适位置
                 1.需交换值后面存在比其大和比其小的数，与比其稍大的值交换，交换后其交换前后一位置之后list逆转  eg[3,4,2,1]  or [2,4,3,1]
                 2.需交换值后面所有值都比其大，与最后一个值交换，交换后其交换前后一位置之后list逆转   eg[1,3,2]
        (2)如果是按最大序排列，则直接逆转  eg[3,2,1]
        """
        len_nums = len(nums)
        for i in range(len_nums - 1, 0, -1):
            if nums[i] > nums[i - 1]:
                # [1,2,3]
                if i == len_nums - 1:
                    nums[i], nums[i - 1] = nums[i - 1], nums[i]
                    return
                else:
                    # [3,4,2,1]  or [2,4,3,1]
                    for j in range(i, len_nums):
                        if nums[i - 1] >= nums[j] and nums[i - 1] < nums[j - 1]:
                            nums[i - 1], nums[j - 1] = nums[j - 1], nums[i - 1]
                            for k in range(0, (len_nums - i) / 2):
                                nums[k + i], nums[len_nums - 1 - k] = \
                                    nums[len_nums - 1 - k], nums[k + i]
                            return
                    # [1,3,2]
                    if nums[i - 1] < nums[len_nums - 1]:
                        nums[i - 1], nums[len_nums - 1] = nums[len_nums - 1], nums[i - 1]
                        for k in range(0, (len_nums - i) / 2):
                            nums[k + i], nums[len_nums - 1 - k] = \
                                nums[len_nums - 1 - k], nums[k + i]
                        return

        # [3,2,1]
        nums.reverse()
        return


if __name__ == "__main__":
    solution = Solution()
    nums = [5, 4, 7, 5, 3, 2]
    solution.nextPermutation(nums)
    print nums
