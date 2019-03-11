package test;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Seen this question in a real interview before?  YesNo
    思路2更好哦
 */
public class Test123 {
    public static void main(String[] args) {
        Test123 t = new Test123();
        int[] prices = {1, 2, 3, 4, 5, 6};
        prices = new int[]{1, 4, 2};
        prices = new int[]{3,3,5,0,0,3,1,4};
//        prices = new int[]{6,1,3,2,4,7};
        prices = new int[]{1,2,4,2,5,7,2,4,9,0};
//        prices = new int[]{1,2,7,4,11};
//        prices = new int[]{1,4,11,7,2};
        prices = new int[]{11,1,2,4,7};
        System.out.println(t.maxProfit(prices));
    }
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[] onceMaxProfit = new int[n];
        int[] twiceMaxProfit = new int[n];
        int[] buyIndexex = new int[n];
        int[] buyIndexexT = new int[n];
        // maxs 记录从buyIndexes[i] 到 i 中的prices的最大值 i包括， buyIndexes不包括
        int[] maxs = new int[n];
        onceMaxProfit[n - 1] = 0;
        onceMaxProfit[n - 2] = Math.max(prices[n - 1] - prices[n - 2], 0);
        // buyIndexex记录once买股票的天下标
        buyIndexex[n - 1] = n - 1;
        buyIndexex[n - 2] = prices[n - 2] <= prices[n - 1] ? n - 2 : n - 1;
        // 记录twice买股票的下标
        buyIndexexT[n - 1] = n - 1;
        buyIndexexT[n - 2] = n - 1;
        maxs[n - 1] = prices[n - 1];
        maxs[n - 2] = prices[n - 2];

        int max = Math.max(onceMaxProfit[n - 1], onceMaxProfit[n - 2]);
        for (int i = n - 3; i >= 0; i --){
//            prices = new int[]{1,2,4,2,5,7,2,4,9,0};
//            prices = new int[]{1,2,7,4,11};
//            prices = new int[]{1,4,11,7,2};
//            prices = new int[]{11,1,2,4,7};
            // 为一次购买选取最优值，第一项为当前天数不买时获取的利益，第二项为今天的价格与上次购买的价格的差价加上上次购买的获益， 第三项为上次购买之前（不包含）到今天（包含）之间的价格的最高值与今天价格的差值。
            onceMaxProfit[i] = tripleMax(onceMaxProfit[i + 1],
                    prices[buyIndexex[i + 1]] - prices[i] + onceMaxProfit[buyIndexex[i + 1]],
                    maxs[i + 1] - prices[i]);

            if (i == n - 3){ twiceMaxProfit[i] = 0; }
            else {
                if(buyIndexex[i + 1] == i + 1){ twiceMaxProfit[i] = twiceMaxProfit[i + 1]; }
                else {
                    twiceMaxProfit[i] = Math.max(twiceMaxProfit[i + 1],
                            maxs[i + 1] - prices[i] + onceMaxProfit[i + 1]);
                }
                twiceMaxProfit[i] = Math.max(twiceMaxProfit[i], prices[buyIndexexT[i + 1]] - prices[i] + twiceMaxProfit[i + 1] );
            }

            if (onceMaxProfit[i] == onceMaxProfit[i + 1]){
                buyIndexex[i] = buyIndexex[i + 1];
                maxs[i] = Math.max(maxs[i + 1], prices[i]);
            }
            else {
                buyIndexex[i] = i;
                maxs[i] = i > 0 ? prices[i - 1] : 0;
            }

            if(twiceMaxProfit[i] == twiceMaxProfit[i + 1]){
                buyIndexexT[i] = buyIndexexT[i + 1];
            }
            else{
                buyIndexexT[i] = i;
            }
            max = tripleMax(max, onceMaxProfit[i], twiceMaxProfit[i]);
//            System.out.println("once = " + onceMaxProfit[i] + ", twice = " + twiceMaxProfit[i] + ", buyIndex = " + buyIndexex[i] + ", maxs = " + maxs[i] + ", max = " + max);
        }
        return max;
    }

    private int tripleMax(int a, int b, int c){
        return Math.max(Math.max(a, b), c);
    }
}
