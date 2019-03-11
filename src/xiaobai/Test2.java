package xiaobai;

import java.util.Scanner;

//新生晚会
public class Test2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int t = 0; t < T; t++){
            int n = input.nextInt();
            int k = input.nextInt();
            System.out.println(fun(n, k));
        }
        input.close();
    }

    public static int fun(int n, int k){
        int[][] dp = new int[2][n + 1];
        if (n < 0 || k < 0 || k > n){
            return 0;
        }
        dp[0][0] = 1;
        for (int i = 1; i <= n; i ++){
            for (int j = 0; j <= i; j ++){
                if (j == 0 || j == i) { dp[i & 1][j] = 1; }
                else {
                    dp[i & 1][j] = dp[(i - 1) & 1][j - 1] + dp[(i - 1) & 1][j];
                    dp[i & 1][j] %= 1000000007;
                }
            }
        }
        return dp[n & 1][k];
    }
}
