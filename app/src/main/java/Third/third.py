# -*- coding: utf-8 -*-
################################################################################
#
# Copyright (c) 2016 Baidu.com, Inc. All Rights Reserved
#
################################################################################

"""
Authors: zhangxiao11(zhangxiao11@baidu.com)
Date:    2017-03-21
"""


class Solution(object):
    """Soultion
    """

    def threeSum(self, nums):
        """threeSum
         Args:
             nums(List[int]): an array to find 3sum is 0.

         Returns:
             List[List[int]]

         Method：
         (1)将nums按照从小到大的顺序排序
         (2)取第i个元素作为目标，i+1作为头，最后一个作为尾，一次比较其和与第i个元素的大小关系，移动头尾指针
            为保证一个目标只匹配一个三元组，需要在匹配完成后，后移头尾指针
            为保证特殊list的数值正确（[0,0,0,0]）,若本目标元素与下一个目标元素相同，则可以略过下一个目标元素
        """
        nums.sort()

        if len(nums) <= 2:
            return list()

        result = list()

        i = 0
        while i <= len(nums) - 2:
            first_num = i + 1
            last_num = len(nums) - 1

            while first_num < last_num:
                if nums[first_num] + nums[last_num] > 0 - nums[i]:
                    last_num -= 1
                    continue
                if nums[first_num] + nums[last_num] < 0 - nums[i]:
                    first_num += 1
                    continue

                truple_item = [nums[i], nums[first_num], nums[last_num]]
                result.append(truple_item)

                # 为保证一个目标只匹配一个三元组，需要在匹配完成后，后移头尾指针
                while (first_num < last_num and truple_item[1] == nums[first_num]):
                    first_num += 1

                while (first_num < last_num and truple_item[2] == nums[last_num]):
                    last_num -= 1
            # 为保证特殊list的数值正确（[0,0,0,0]）,若本目标元素与下一个目标元素相同，则可以略过下一个目标元素
            while i < len(nums) - 1 and nums[i] == nums[i + 1]:
                i += 1

            i += 1

        return result


if __name__ == "__main__":
    solution = Solution()
    nums = [0, 0, 0, 0]
    print solution.threeSum(nums)
