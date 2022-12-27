package test;

public class Test629_3 {
    public int kInversePairs(int n, int k) {
        if (n <= 0) return 0;
        if (0 == k) return 1;
        if (1 == k) return n - 1;

        int[][] dp0 = new int[n + 1][k + 1];
        int[][] dp1 = new int[n + 1][k + 1];

        final int MOD = (int)(1e9 + 7);

        for (int i = 1; i <= n; i ++) {
            dp0[i][0] = i;
            dp1[i][0] = 1;
            dp1[i][1] = i;
        }

        for (int i = 2; i <= n; i ++) {
            for (int j = 1; j <= k; j ++) {
                int index = Math.max(j - i, -1);
                int t = -1 == index ? dp1[i][j - 1]: (dp1[i][j - 1] + MOD - dp1[i][index]);
                t %= MOD;
                dp0[i][j] = (dp0[i - 1][j] + dp0[i - 1][j - 1]) % MOD + t;
                dp0[i][j] %= MOD;
                dp1[i][j] = dp0[i - 1][j - 1] + dp1[i][j - 1];
                dp1[i][j] %= MOD;
            }
        }
        return dp0[n - 1][k - 1];
    }

    public static void main(String[] args) {
        Test629_3 t = new Test629_3();
        int result = t.kInversePairs(1000, 1000);
//        int result = t.kInversePairs(45, 67);

        System.out.println("result = " + result);
    }
}
