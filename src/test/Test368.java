package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]
*
* */
public class Test368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        int[] next = new int[nums.length];
        int[] dp = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i --){
            next[i] = nums.length;
            for (int j = i + 1; j < dp.length; j ++){
                if (nums[j] % nums[i] == 0){
                    if (dp[i] < dp[j] + 1){
                        dp[i] = dp[j] + 1;
                        next[i] = j;
                    }
                }
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < dp.length; i ++){
            if (dp[i] > dp[maxIndex]){
                maxIndex = i;
            }
        }

        for (int i = maxIndex; i < dp.length; i = next[i]){
            result.add(nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Test368 t = new Test368();
        int[] nums = {1, 2, 3};
        nums = new int[]{1, 2, 4, 7, 8};
        nums = new int[0];
        System.out.println(t.largestDivisibleSubset(nums));
    }
}
