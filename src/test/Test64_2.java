package test;

/*
*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
[[1,3,1],
 [1,5,1],
 [4,2,1]]
Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
*
*
* [[1,2],[5,6],[1,1]]
* */
public class Test64_2 {
    public static void main(String[] args){
        Test64_2 t = new Test64_2();
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        grid = new int[][]{
                {1,2},
                {5,6},
                {1,1}
        };

        grid = new int[][]{
                {1,2,5},
                {3,2,1}
        };
        System.out.println(t.minPathSum(grid));
    }
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length <= 0 || grid[0] == null || grid[0].length <= 0){ return 0; }
        int n = grid.length;
        int m = grid[0].length;
        int[][] s = new int[2][m];
        s[0][0] = grid[0][0];
        for(int i = 1; i < m; i ++){ s[0][i] = s[0][i - 1] + grid[0][i]; }

        for(int i = 1; i < n; i ++){
            s[i & 1][0] = s[i - 1 & 1][0] + grid[i][0];
//            System.out.print(s[i & 1][0] + " ");
            for(int j = 1; j < m; j ++){
                s[i & 1][j] = Math.min(s[i - 1 & 1][j], s[i & 1][j - 1]) + grid[i][j];
//                System.out.print(s[i & 1][j] + " ");
            }
//            System.out.println();
        }
        return s[n - 1 & 1][m - 1];
    }
}
