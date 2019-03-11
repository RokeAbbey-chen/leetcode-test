package test;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Seen this question in a real interview before?  YesNo
 思路2更好哦
 */
public class Test123_2 {
    public static void main(String[] args) {
        Test123_2 t = new Test123_2();
        int[] prices = {1, 2, 3, 4, 5, 6};
        prices = new int[]{1, 4, 2};
        prices = new int[]{3,3,5,0,0,3,1,4};
//        prices = new int[]{6,1,3,2,4,7};
        prices = new int[]{1,2,4,2,5,7,2,4,9,0};
//        prices = new int[]{1,2,7,4,11};
//        prices = new int[]{1,4,11,7,2};
        prices = new int[]{11,1,2,4,7};
        prices = new int[]{3,2,6,5,0,3};
        System.out.println(t.maxProfit(prices));
    }
    public int maxProfit(int[] prices) {
//        [3,2,6,5,0,3]
        int n = prices.length;
        if(n <= 1){ return 0; }
        int[][] local = new int[n][3];
        int[][] global = new int[n][3];
        for (int i = 1; i < n; i ++){
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j < 3; j ++){
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0)
                        , local[i - 1][j] + diff);
                global[i][j] = Math.max(local[i][j], global[i - 1][j]);
//                System.out.println("local[" + i + "][" + j + "] = " + local[i][j] + ", global[" + i + "][" + j + "] = " + global[i][j]);
            }
        }
        return global[n - 1][2];
    }
}
