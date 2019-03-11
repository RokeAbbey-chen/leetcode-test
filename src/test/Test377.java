package test;

import java.util.Arrays;

/*
* Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
*
* */
public class Test377 {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0){ return 0; }
        int[] indexes = new int[nums.length];
        int len = 0;
        for(int i = 0; i < nums.length; i ++){
            if (nums[i] <= target && len < nums[i]){
                len = nums[i];
            }
        }
        len ++;
        int[] dp = new int[len];
        for (int i = 1; i <= target; i ++){
            dp[i % len] = 0;
            for (int j = 0; j < indexes.length; j ++){
                if (indexes[j] + nums[j] == i){
                    if (indexes[j] == 0){
                        dp[i % len] ++;
                    }
                    else {
                        dp[i % len] += dp[indexes[j] % len];
                    }
                    indexes[j] ++;
                }
            }
            System.out.println(dp[i % len]);
        }
        return dp[target % len];
    }

    public static void main(String[] args) {
        Test377 t = new Test377();
        int[] num = {1, 2, 3};
        int target = 10;
        System.out.println(t.combinationSum4(num, target));
    }
}
