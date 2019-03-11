package xiaobai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//第二题
public class Test3 {
    public static void main(String[] args) {
//        Test3 test3 = new Test3();
//        int n = 3;
//        int[] B = {0, 1, 1, 1};
//
//        n = 5;
//        B = new int[]{0, 28, 26, 25, 24, 1};
//
//        n = 10;
//        B = new int[]{0, 996, 901, 413, 331, 259, 241, 226, 209, 139, 49};

        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int t = 0; t < T; t ++){
            int n = input.nextInt();
            int[] B = new int[n + 1];
            B[0] = 0;
            for (int i = 1; i <= n; i ++){
                B[i] = input.nextInt();
            }
            System.out.println(Test3.solve(n, B));
        }
    }
    public static int solve(int n, int[] B){
        for (int i = n; i >= 1; i --){
            B[i - 1] += B[i];
        }

        int[][] dp = new int[2][n + 2];
        Arrays.fill(dp[0], 11037);
        Arrays.fill(dp[1], 11037);
        dp[1][1] = 0;
        for (int i = 1; i <= n; i ++){
            for (int j = 1; j <= n; j ++){
                if (i == 1 && j == 1){
                    dp[1][1] = 0;
                }
                else if (i >= 1 && i <= n && j >= 1 && j <= n){
                    if (j + 1 >> 1 != j) {
                        dp[i & 1][j] = Math.min(dp[(i - 1) & 1][j + 1], dp[i & 1][(j + 1) >> 1] + B[i]);
                    }
                    else{
                        dp[i & 1][j] = dp[(i - 1) & 1][j + 1];
                    }
                }
                else {
                    dp[i & 1][j] = 11037;
                }
                if (i == n && j == 1){
                    return dp[i & 1][1];
                }
            }
        }
        return dp[n & 1][1];
    }
}
