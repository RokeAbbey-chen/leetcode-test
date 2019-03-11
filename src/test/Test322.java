package test;

/*
* You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
*
* 空间超限
* */
public class Test322 {
    public int coinChange(int[] coins, int amount) {
        if (coins.length < 1 || amount <= 0){ return 0; }
        int[] indexes = new int[coins.length];
        int maxCoin = 0;
        int minCoin = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i ++){
            maxCoin = Math.max(maxCoin, coins[i]);
            minCoin = Math.min(minCoin, coins[i]);
        }
        if (minCoin > amount){
            return -1;
        }

        int[] dp = new int[maxCoin];
        for (int i = 1; i <= amount; i ++){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < indexes.length; j ++){
                if (indexes[j] + coins[j] < i){
                    indexes[j] ++ ;
                }
                if (indexes[j] + coins[j] == i
                        && dp[indexes[j] % maxCoin] != Integer.MAX_VALUE) {
                    if (min > dp[indexes[j] % maxCoin] + 1){
                        min = dp[indexes[j] % maxCoin] + 1;
                        minIndex = j;
                    }
                }
            }
            dp[i % maxCoin] = min;
            if (minIndex != -1){
                indexes[minIndex] ++ ;
            }
        }
        return dp[amount % maxCoin] == Integer.MAX_VALUE ? -1 : dp[amount % maxCoin];

    }

    public static void main(String[] args) {
        Test322 t = new Test322();
        int[] nums = {1, 2, 5};
        int amount = 11;
        System.out.println(t.coinChange(nums, amount));
    }
}
