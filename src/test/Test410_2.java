package test;

/**
 * 名次低
 */
public class Test410_2 {
    public int splitArray(int[] nums, int m) {
        int[][] dp = new int[2][nums.length];
        int[] sums = new int[nums.length];
        dp[1][0] = sums[0] = nums[0];

        for (int i = 1; i < nums.length; i ++){
            dp[1][i] = dp[1][i - 1] + nums[i];
            sums[i] = dp[1][i];
        }
        for (int i = 2; i <= m; i ++){
            for (int j = i - 1; j < nums.length; j ++) {
                dp[i & 1][j] = Integer.MAX_VALUE;
                for (int k = j - 1; k >= i - 2; k --) {
                    dp[i & 1][j] = Math.min(dp[i & 1][j], Math.max(dp[(i - 1) & 1][k], sums[j] - sums[k]));
                }
            }
        }
        return dp[m & 1][nums.length - 1];
    }
}
