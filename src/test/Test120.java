package test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*
* */
public class Test120 {
    public static void main(String[] args) {
        Test120 t = new Test120();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

//        [[-1],
//         [2,3],
//          [1,-1,-3]]
//        triangle = new ArrayList<>();
//        triangle.add(Arrays.asList(-1));
//        triangle.add(Arrays.asList(2, 3));
//        triangle.add(Arrays.asList(1, -1, -3));
        System.out.print(t.minimumTotal(triangle));

    }
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0 || triangle.get(0) == null || triangle.get(0).size() == 0 ) { return 0; }
        int n = triangle.size();
        int[][] dp = new int[2][n];
        dp[0][0] =  triangle.get(0).get(0);
        for(int i = 1; i < n; i++){
            List<Integer> curRow = triangle.get(i);
            dp[i & 1][0] = dp[(i - 1) & 1][0] + curRow.get(0);
            dp[i & 1][i] = dp[(i - 1) & 1][i - 1] + curRow.get(i);
            for(int j = 1; j < i; j++){
                dp[i & 1][j] = Math.min(dp[(i - 1) & 1][j - 1], dp[(i - 1) & 1][j]) + curRow.get(j);
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            min = Math.min(min, dp[(n - 1) & 1][i]);
        }
        return min;
    }
}
