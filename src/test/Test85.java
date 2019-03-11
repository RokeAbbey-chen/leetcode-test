package test;

/*
* Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*
* */
public class Test85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int max = 0;
        Rectangle[][] dp = new Rectangle[2][matrix[0].length];
        for (int i = 0; i < matrix[0].length; i ++){
            dp[0][i] = new Rectangle();
            if (matrix[0][i] == '1'){
                dp[0][i].refresh(1, 1);
            }
        }

        for (int i = 0; i < matrix[0].length; i ++){
            dp[1][i] = new Rectangle();
        }

        for (int i = 1; i < matrix.length; i ++){
            for (int j = 0; j < matrix[i].length; j ++){
                if (matrix[i][j] == '0'){
                    dp[i & 1][j].refresh();
                }
                else if (j == 0){
                    dp[i & 1][0].refresh(1, 1);
                }
                else {
                    int width2 = Math.min(dp[i & 1][j].width + 1, dp[(i - 1) & 1][j].width);
                    int height2 = dp[(i - 1) & 1][j].height + 1;
                    int area2 = width2 * height2;
                }
            }
        }
        return 0;
    }

    private static class Rectangle{
        public int width, height;
        public Rectangle(){

        }

        public Rectangle(int width, int height){
            this.width = width;
            this.height = height;
        }

        public int area(){
            return width * height;
        }

        public void refresh(){
            refresh(0, 0);
        }

        public void refresh(int width, int height){
            this.width = width;
            this.height = height;
        }

    }
}
