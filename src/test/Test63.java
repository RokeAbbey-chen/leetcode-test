package test;

/*
*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.

*
* */
public class Test63 {
    public static void main(String[] args) {
        Test63 t = new Test63();
        int[][] obs = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        obs = new int[][]{
                {0,1,0,0,0},
                {1,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
//        [[0,1,0,0,0],[1,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]
        System.out.println(t.uniquePathsWithObstacles(obs));
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0
                || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1
                || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1) { return 0; }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[2][n];
        dp[0][0] = 1;

        for(int i = 1; i < n; i ++){ dp[0][i] = obstacleGrid[0][i - 1] == 1 ? 0 : dp[0][i - 1]; }

        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++) {
                dp[i & 1][j] =
                        (j == 0 || obstacleGrid[i][j - 1] == 1 ? 0 : dp[i & 1][j - 1])
                        + (obstacleGrid[i - 1][j] == 1 ? 0 : dp[i - 1 & 1][j]);
            }
        }
        return  dp[m - 1 & 1][n - 1];

    }
}
