

package test;

import java.util.*;

/**
 * 552. Student Attendance Record II
 Hard

 Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

 A student attendance record is a string that only contains the following three characters:

 'A' : Absent.
 'L' : Late.
 'P' : Present.

 A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

 Example 1:

 Input: n = 2
 Output: 8
 Explanation:
 There are 8 records with length 2 will be regarded as rewardable:
 "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 Only "AA" won't be regarded as rewardable owing to more than one absent times.

 Note: The value of n won't exceed 100,000.
 Accepted
 10,501
 Submissions
 32,921
 */
public class Test552 {

    private static final int MODDER = (int)Math.pow(10, 9) + 7;
    private static final int L = 1;
    private static final int LL = 2;
    private static final int P = 3;
    private static final int A = 4;
    public int checkRecord(int n) {

        //分为有A情况与无A情况
        int[][] dpA = new int[2][5];
        int[][] dp = new int[2][5];
        dp[1][L] = dp[1][P] = 1;

        dpA[1][A] = 1;
        dpA[1][L] = dpA[1][P] = 0;

        for (int i = 2; i <= n; i ++ ){
            // 无A情况
            dp[i & 1][L] = dp[(i - 1) & 1][P];
            dp[i & 1][LL] = dp[(i - 1) & 1][L];
            dp[i & 1][P] = ((dp[(i - 1) & 1][P] + dp[(i - 1) & 1][L]) % MODDER + dp[(i - 1) & 1][LL]) % MODDER;

            // 有A情况
            if (i == 2){ dpA[i & 1][LL] = 0; }
            else { dpA[i & 1][LL] = dpA[(i - 1) & 1][L]; }
            dpA[i & 1][L] = (dpA[(i - 1) & 1][A] + dpA[(i - 1) & 1][P]) % MODDER;
            dpA[i & 1][A] = ((dp[(i - 1) & 1][P] + dp[(i - 1) & 1][L]) % MODDER + dp[(i - 1) & 1][LL]) % MODDER;
            dpA[i & 1][P] = (((dpA[(i - 1) & 1][A] + dpA[(i - 1) & 1][L])) % MODDER + (dpA[(i - 1) & 1][LL] + dpA[(i - 1) & 1][P]) % MODDER) % MODDER;
        }
        int result = 0;
        for (int i = 0; i < dp[n & 1].length; i ++){
            result = (result + (dp[n & 1][i] + dpA[n & 1][i]) % MODDER) % MODDER;
        }
        return (int)result;
    }


    public static void main(String[] args) {
        Test552 t = new Test552();
        int n = 1000;
        System.out.println(t.checkRecord(n));
    }
}

