package test;

import java.util.Arrays;

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
* */
public class Test64 {
    private int[][] where;
    private int lastRow = -1;
    private int lastCol = -1;
    public static void main(String[] args){
        Test64 t = new Test64();
        int[][] grid = {{1,3,1}, {1,5,1}, {4,2,1}};
        grid = new int[][]
{
{5,4,2,9,6,0,3,5,1,4,9,8,4,9,7,5,1},
{3,4,9,2,9,9,0,9,7,9,4,7,8,4,4,5,8},
{6,1,8,9,8,0,3,7,0,9,8,7,4,9,2,0,1},
{4,0,0,5,1,7,4,7,6,4,1,0,1,0,6,2,8},
{7,2,0,2,9,3,4,7,0,8,9,5,9,0,1,1,0},
{8,2,9,4,9,7,9,3,7,0,3,6,5,3,5,9,6},
{8,9,9,2,6,1,2,5,8,3,7,0,4,9,8,8,8},
{5,8,5,4,1,5,6,6,3,3,1,8,3,9,6,4,8},
{0,2,2,3,0,2,6,7,2,3,7,3,1,5,8,1,3},
{4,4,0,2,0,3,8,4,1,3,3,0,7,4,2,9,8},
{5,9,0,4,7,5,7,6,0,8,3,0,0,6,6,6,8},
{0,7,1,8,3,5,1,8,7,0,2,9,2,2,7,1,5},
{1,0,0,0,6,2,0,0,2,2,8,0,9,7,0,8,0},
{1,1,7,2,9,6,5,4,8,7,8,5,0,3,8,1,5},
{8,9,7,8,1,1,3,0,1,2,9,4,0,1,5,3,1},
{9,2,7,4,8,7,3,9,2,4,2,2,7,8,2,6,7},
{3,8,1,6,0,4,8,9,8,0,2,5,3,5,5,7,5},
{1,8,2,5,7,7,1,9,9,8,9,2,4,9,5,4,0},
{3,4,4,1,5,3,3,8,8,6,3,5,3,8,7,1,3}} ;
        System.out.println(t.minPathSum(grid));
        for(int[] p : t.where){
            for(int i : p) {System.out.print(i + " ");}
            System.out.println();
        }

        System.out.println(t.check(grid));
    }
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) { return 0; }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        where = new int[m][n];
        init(dp, Short.MAX_VALUE);
        fun(dp, grid, 0, 0, 0);
        return dp[m - 1][n - 1];
    }
    private void init(int[][] ars, int v){
        for(int[] a : ars){
            for(int i = 0; i < a.length; i ++){ a[i] = v; }
        }
    }
    private void fun(int[][] dp, int[][] grid, int row, int col, int weight){
        if(row < 0 || row >= dp.length || col < 0 || col >= dp[0].length) { return; }
        if(dp[row][col] - grid[row][col] > weight){
            where[row][col] = lastRow * 1000 + lastCol;
            dp[row][col] = grid[row][col] + weight;
            lastRow = row; lastCol = col;
            fun(dp, grid, row, col + 1, dp[row][col]);
            lastRow = row; lastCol = col;
            fun(dp, grid, row, col - 1, dp[row][col]);
            lastRow = row; lastCol = col;
            fun(dp, grid, row - 1, col, dp[row][col]);
            lastRow = row; lastCol = col;
            fun(dp, grid, row + 1, col, dp[row][col]);
        }
    }

    private int check(int[][] grid){
       int sum = 0;
       int row = where.length - 1;
       int col = where[0].length - 1;
       while(row >= 0 || col >= 0 ){
           sum += grid[row][col];
           System.out.print(grid[row][col] + ", ");
           int tempCol = col;
           tempCol = where[row][col] % 1000;
           row = where[row][col] / 1000;
           col = tempCol;
       }
       sum += grid[0][0];
       return sum;
    }
}
