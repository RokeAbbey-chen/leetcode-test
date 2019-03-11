package test;

/*
*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
    (ie, buy one and sell one share of the stock multiple times).
    However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

*
* */
public class Test122 {
    public static void main(String[] args) {
        Test122 t = new Test122();
        int[] prices = {
                1,2,3,4,5,6
        };
        System.out.println(t.maxProfit(prices));
    }
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1){ return 0;}

        int[] maxProfit = new int[n];
        maxProfit[n - 1] = 0;
        maxProfit[n - 2] = prices[n - 1] - prices[n - 2];
        for (int i = n - 3; i >= 0; i --){
            // last last Profit
            int llMaxProfit = maxProfit[i + 2] <= 0 ? 0 : maxProfit[i + 2];
            maxProfit[i] = tripleMax(prices[i + 1] - prices[i] + llMaxProfit
                    , prices[i + 2] - prices[i] + llMaxProfit
                    , maxProfit[i + 1] );
        }
        return maxProfit[0] <= 0 ? 0 : maxProfit[0];
    }

    private int tripleMax(int a, int b, int c){
        return Math.max(Math.max(a, b), c);
    }
}
