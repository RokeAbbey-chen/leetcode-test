package test;

import java.util.Arrays;

public class Test377_2 {
    private int[] dp;
    public int combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, 0);
        dp[0] = 0;
        fun(nums, target);
        return dp[target];
    }

    public void fun(int[] nums, int target){
        if (target < 0){
            return;
        }
        if (dp[target] != 0){
            return;
        }
        for (int i = 0; i < nums.length; i ++){
            if (target - nums[i] > 0) {
                fun(nums, target - nums[i]);
                dp[target] += dp[target - nums[i]];
            }
            else if (target == nums[i]){
                dp[target] ++ ;
            }
        }
    }

    public static void main(String[] args) {
        Test377_2 t = new Test377_2();
        int[] nums = {1, 2, 3};
        int target = 10;
        System.out.println(t.combinationSum4(nums, target));
    }
}
