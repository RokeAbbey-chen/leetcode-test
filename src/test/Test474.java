package test;

import java.util.ArrayList;
import java.util.List;

public class Test474 {
    /*
    *
    * ["10","0001","111001","1","0"]
5
3
    * */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int c0 = 0;
            int c1 = 0;

            for (int i = 0; i < s.length(); i ++){
                if (s.charAt(i) == '0'){ c0 ++; }
                else { c1 ++; }
            }

            for (int i = m; i >= c0 ; i --){
                for (int j = n; j >= c1; j --){
                    dp[i][j] = Math.max(dp[i][j], dp[i - c0][j - c1] + 1);
                }
            }
        }

        return dp[m][n];
    }

}
