package test;

import java.util.Arrays;

/*
* We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

Credits:
Special thanks to @agave and @StefanPochmann for adding this problem and creating all test cases.
*
* 记忆式递归
*
* */
public class Test375 {
    int[][] dp;
    public int getMoneyAmount(int n) {
        if (dp == null) {
            dp = new int[n + 1][n + 1];
            for (int i = 0; i <= n; i ++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
        }
        else {
            int[][] newDp = new int[n + 1][n + 1];
            for (int i = 0; i < dp.length; i ++){
                for (int j = 0; j < dp[i].length; j ++){
                    newDp[i][j] = dp[i][j];
                }
            }
            dp = newDp;
        }

        return getMoneyAmount(1, n);
    }

    public int getMoneyAmount(int start, int end){

        if (end == start){
            return 0;
        }
        if (end - start == 1){
            return start;
        }
        if (dp[start][end] != Integer.MAX_VALUE){
            return dp[start][end];
        }
        for (int i = start; i <= end; i ++){
            if (i == start){
                dp[start][end] = getMoneyAmount(i + 1, end) + i;
            }
            else if (i == end){
                dp[start][end] = Math.min(getMoneyAmount(start, i - 1) + i, dp[start][end]);
            }
            else {
                dp[start][end] = Math.min(dp[start][end], Math.max(getMoneyAmount(start, i - 1), getMoneyAmount(i + 1, end)) + i);
            }
        }
        return dp[start][end];
    }

    public static void main(String[] args) {
        Test375 t = new Test375_2();
        int n = 10;
        n = 15;
//        n = 6;
//        n = 20;
        n = 100;
//        n = 62;
//        n = 1;
        System.out.println(t.getMoneyAmount(n));
    }
}
