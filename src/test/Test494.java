package test;

import java.util.HashMap;
import java.util.HashSet;

/*
* You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*
* */
public class Test494 {
    public static void main(String[] args) {
        Test494 t = new Test494();
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;

        nums = new int[]{1,8,8,3,5,2,2,6,6,7};
        S = 8;
        System.out.println(t.findTargetSumWays(nums, S));
    }
    public int findTargetSumWays(int[] nums, int S) {
        int maximum = 0;
        for (int i : nums){ maximum += i; }
        if (S < -maximum || maximum < S){ return 0; }

        return findTargetSumWays(nums, S, 0, maximum);
    }

    public int findTargetSumWays(int[] nums, int S, int useNum, int maximum){
        if (useNum == nums.length ) {
            if (S == 0) { return 1; }
            else { return 0; }
        }
        return findTargetSumWays(nums, S - nums[useNum], useNum + 1, maximum)
                + findTargetSumWays(nums, S + nums[useNum], useNum + 1, maximum);
    }
}
