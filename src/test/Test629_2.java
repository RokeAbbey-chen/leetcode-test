package test;

public class Test629_2 {

    public int kInversePairs(int n, int k) {
        if (n <= 0 || k < 0) return 0;
        if (0 == k) return 1;
        if (1 == k) return n - 1;

        final int MOD = (int)(1e9 + 7);
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i < dp.length; i ++) {
            dp[i][0] = 1 + dp[i - 1][0];
            dp[i][1] = i - 1 + dp[i - 1][1];
        }

        for (int p = 1; p <= n; p ++) {
            for (int t = 2; t <= k; t ++) {
                int sum = 0;
                for (int i = 1; i <= t && i <= p; i ++) {
                    int s = dp[p - 1][t - i] - dp[i - 1][t - i];
                    s %= MOD; sum += s; sum %= MOD;
                }
                dp[p][t] = sum + dp[p - 1][t];
                dp[p][t] %= MOD;
            }
        }
        return (dp[n][k] - dp[n - 1][k] + MOD) % MOD;
    }

    public static void main(String[] args) {

        Test629_2 test = new Test629_2();
//        int result = test.kInversePairs(1000, 400);
        int result = test.kInversePairs(1000, 1000);
        System.out.println("result = " + result);
    }
}
