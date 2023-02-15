package test;

public class Test730_2 {
    public int countPalindromicSubsequences(String s) {
        final int MOD = (int)(1e9 + 7);
        final int len = s.length();
        char[] chs = s.toCharArray();
        int[][][] dp = new int[len][len][4];
        for (int i = 0; i < len; i ++) {
            dp[i][i][chs[i] - 'a'] = 1;
            for (int j = i - 1; j >= 0 ; j --) {
                for (int k = 0; k < 4; k ++) {
                    char ck = (char)('a' + k);
                    int tmp;
                    if (ck != chs[i] && ck != chs[j]) {
                        tmp = j + 1 <= i - 1 ? dp[i - 1][j + 1][k]: 0;
                    } else if (ck == chs[i] && ck == chs[j]) {
                        tmp = 2;
                        if (j + 1 <= i - 1) {
                            int[] arr = dp[i - 1][j + 1];
                            tmp += (((arr[0] + arr[1]) % MOD + arr[2]) % MOD + arr[3]) % MOD;
                            tmp %= MOD;
                        }
                    }
                    else if (ck == chs[i]) {
                        tmp = j + 1 <= i ? dp[j + 1][i][k]: 1;
                    } else {
                        tmp = j <= i - 1 ? dp[j][i - 1][k]: 1;
                    }

                    dp[i][j][k] = dp[j][i][k] = tmp;
                }
            }
        }

        int[] arr = dp[0][len - 1];
        int result = (((arr[0] + arr[1]) % MOD + arr[2]) % MOD + arr[3]) % MOD;
        return result;
    }

    public static void main(String[] args) {
        Test730_2 t = new Test730_2();
        String s = "abccdcadadacddabcabbbcccabcabdddcabbcacabbabcbd";
//        String s = "abccdcada";
//        String s = "ccdca";
//        String s = "ccdc";
//        String s = "ccd";
        int result = t.countPalindromicSubsequences(s);
        System.out.println("result:" + result);
    }
}
