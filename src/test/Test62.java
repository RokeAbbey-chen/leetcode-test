package test;

/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?*/
public class Test62 {
    public static void main(String[] args){
        Test62 t = new Test62();
//        System.out.println(t.uniquePaths(23, 12));
        System.out.println(t.uniquePaths(3, 7));
    }
//    public int uniquePaths(int m, int n) {
//        if(m == 1 || n == 1){
//            if(m == n) { return 1;}
//            if(m == 1) {return uniquePaths(m, n - 1);}
//            else {return uniquePaths(m - 1, n);}
//        }
//        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
//    }

    public int uniquePaths(int m, int n){
        int[][] dp = new int[2][n];
        for(int j = 0; j < n; j++){dp[0][j] = 1;}
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++) {
                dp[i & 1][ j] = j == 0
                        ? dp[i - 1 & 1][j]
                        : dp[i & 1][j - 1] + dp[i - 1 & 1][j];
            }
        }
        return dp[m - 1 & 1][n - 1];
    }
}
