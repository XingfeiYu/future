package com.future.round2;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number,
 * target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

 For example, given array S = {-1 2 1 -4}, and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Created by someone on 10/11/17.
 */
public class Problem16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if(sum == target) {
                    return sum;
                } else if(sum > target) {
                    right--;
                } else {
                    left++;
                }
                if(min > Math.abs(sum - target)) {
                    min = Math.abs(sum - target);
                    res = sum;
                }
            }
        }
        return res;
    }
}
