package test;

import java.util.Arrays;

/*
* Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
*
* */
public class Test188 {
    public int maxProfit(int k, int[] prices) {

        int[] diff = new int[prices.length];
        int topIndex = 0;
        int bottomIndex = 0;
        int len = 0;

        int sum = 0;
        for (int i = 1; i < prices.length; i ++){
            if(prices[i] > prices[i - 1]){
                topIndex = i;
                if (i + 1 >= prices.length || prices[i + 1] < prices[i]){
                    sum += prices[topIndex] - prices[bottomIndex];
                }
            }
            else{
                bottomIndex = i;
                if (topIndex != 0 && i + 1 < prices.length && prices[i + 1] > prices[i]){
                    diff[len ++] =  prices[topIndex] - prices[bottomIndex];
                    System.out.println("diff1 : " + diff[len - 1]);
                }
            }
        }


        if (k >= len + 1){ return sum; }
        Arrays.sort(diff, 0, len);
        for (int i = 0; i < len - (k - 1); i ++){
            sum -= diff[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Test188 t = new Test188();
        int[] nums = new int[]{3,2,6,5,0,3};
        int k = 2;
        nums = new int[]{3,3,5,0,0,3,1,4};
        nums = new int[]{6,1,6,4,3,0,2};
        k = 1;
        System.out.println(t.maxProfit(k, nums));

    }
}
