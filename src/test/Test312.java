package test;

/*
*
* Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

*
* */
public class Test312 {

    public int maxCoins(int[] nums) {
        int[] arr = new int[nums.length + 2];
        System.arraycopy(nums, 0, arr, 1, nums.length);
        arr[0] = arr[ nums.length  + 1] = 1;
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int len = 3; len <= n; len ++){
            for (int l = 0, r = len - 1; r < n; l ++, r = l + len - 1){
                for (int mid = l + 1; mid < r; mid ++){
                    dp[l][r] = Math.max(dp[l][r], dp[l][mid] + dp[mid][r] + arr[l] * arr[r] * arr[mid]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Test312 t = new Test312();
        int[] nums = {3, 1, 5, 8};
        System.out.println(t.maxCoins(nums));
    }
}
