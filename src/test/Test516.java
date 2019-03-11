package test;

/**
 * 516. Longest Palindromic Subsequence
 Medium

 Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

 Example 1:
 Input:

 "bbbab"

 Output:

 4

 One possible longest palindromic subsequence is "bbbb".

 Example 2:
 Input:

 "cbbd"

 Output:

 2

 One possible longest palindromic subsequence is "bb".
 */
public class Test516 {

    public int longestPalindromeSubseq(String s) {
        char[] chs = s.toCharArray();
        if (chs.length <= 0){ return 0; }
        int[][] dp = new int[chs.length][chs.length];
        dp[0][0] = 1;
        for (int i = 1; i < chs.length; i ++){
            dp[i][i] = 1;
            dp[i][i - 1] = chs[i] == chs[i - 1] ? 2 : 1;
            for (int j = i - 2; j >= 0; j --){
                dp[i][j] = chs[i] == chs[j] ? dp[i - 1][j + 1] + 2 : Math.max(dp[i][j + 1], dp[i - 1][j]);
            }
        }
        return dp[chs.length - 1][0];
    }
}
