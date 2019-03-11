package test;

/*
* Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
*
* */
public class Test221 {
    public static void main(String[] args) {
        Test221 t = new Test221();
        char[][] matrix = {
                 {'1','0','1','0'},
                 {'1','0','1','1'},
                 {'1','0','1','1'},
                 {'1','1','1','1'}
        };
        System.out.println(t.maximalSquare(matrix));

// ["1","0","1","0"],
// ["1","0","1","1"],
// ["1","0","1","1"],
// ["1","1","1","1"]
    }
    public int maximalSquare(char[][] matrix) {
        if (matrix.length <= 0 || matrix[0].length <= 0){ return 0; }
        int[][] dp = new int[2][matrix[0].length];
        int maxLen = 0;
        for (int i = 0; i < dp[0].length; i ++){
            if (matrix[0][i] == '1'){
                dp[0][i] = 1;
                maxLen = 1;
            }
        }
        for (int i = 1; i < matrix.length; i ++){
            for (int j = 0; j < matrix[i].length; j ++){
                if (j == 0){
                    dp[i & 1][0] = matrix[i][0] == '1' ? 1 : 0;
                }
                else if (matrix[i][j] == '1'){
                    dp[i & 1][j] = tripleMin(dp[i & 1][j - 1], dp[(i - 1) & 1][j], dp[(i - 1) & 1][j - 1]) + 1;
                }
                else {
                    dp[i & 1][j] = 0;
                    if ( j >=  matrix[i].length - maxLen){
                        break;
                    }
                }
                maxLen = Math.max(dp[i & 1][j], maxLen);
            }
        }
        return maxLen * maxLen;
    }
    private int tripleMin(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }
//    [
// ["1","0","1","0"],
// ["1","0","1","1"],
// ["1","0","1","1"],
// ["1","1","1","1"]]

}
