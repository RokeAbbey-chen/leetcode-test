package test;

public class Test730 {
    public int countPalindromicSubsequences(String s) {
        final int MOD = (int)(1e9 + 7);
        final int len = s.length();
        char[] chs = s.toCharArray();
        int[][] dp = new int[len][len];
        int result = 0;
        System.out.println("s = " + s);
        boolean[] flags = {false, false, false, false};
        for (int i = 0; i < len; i ++) {
            dp[i][i] = 1;
            if (!flags[chs[i] - 'a']) {
                result += dp[i][i];
                result %= MOD;
                flags[chs[i] - 'a'] = true;
            }
            System.out.println("s = " + s + ", chs[i] = " + chs[i]);
            System.out.println("i = " + i + ", result = " + result + ", dpii = " + dp[i][i]);
            int k = i;
            for (int j = i - 1; j >= 0; j --) {
                System.out.println("i = " + i + ", j = " + j );
                if (chs[i] == chs[j]) {
                    int count = i - 1 >= j + 1 ? dp[i - 1][j + 1] : 0;
                    count = (count + 1) % MOD;
                    result += count;
                    System.out.println("chs[j] = " + chs[j] + ", count = " + count);
                    if (k != i) {
                        if (k + 1 <= i - 1) {

                            System.out.println("dpik = " + dp[i - 1][k + 1]);
                            result = (result - dp[i - 1][k + 1]) % MOD;
                            result %= MOD;
                            System.out.println("result = " + result);
                        }
                        if (j + 1 <= k - 1) {
                            System.out.println("dpjk = " + dp[j + 1][k - 1]);
                            result = (result - dp[j + 1][k - 1]) % MOD;
                            result %= MOD;
                            System.out.println("result = " + result);
                        }
//                        System.out.println("chs[j] = " + chs[j] + ", dpij = " + dp[i][j] + ", dp[i][k] = " + dp[i][k] + ", dp[j][k] = " + dp[j][k] + ", result = " + result);
                    }
//                    result += (((count - dp[i][k]) % MOD - dp[j][k]) % MOD + 1) % MOD;
//                    result %= MOD;
                    dp[i][j] = dp[j][i] = count;
                    k = j;
                } else {
                    int count = i - 1 >= j + 1 ? dp[i - 1][j + 1] : 0;
                    dp[i][j] = dp[j][i] = count;
                    System.out.println("else: count = " + count);
                }
            }
            System.out.println("---");
        }
        return result;
    }

    public static void main(String[] args) {
        Test730 t = new Test730();
        String s = "abccdca";
        int result = t.countPalindromicSubsequences(s);
        System.out.println("result:" + result);
    }
}
