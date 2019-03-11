package test;

public class Test494_2 extends Test494{

    @Override
    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums){ sum += n; }
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >> 1);
    }

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = s; i >= n; i--) {
                dp[i] += dp[i - n];
                System.out.println("dp[i] = " + dp[i] + ", i = " + i);
            }
        }
        return dp[s];
    }


    public static void main(String[] args) {
        Test494 t = new Test494_2();
        int[] nums = {1, 1, 1, 1, 2};
        int S = 2;
        System.out.println(t.findTargetSumWays(nums, S));
    }

}