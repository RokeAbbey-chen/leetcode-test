package test;

import java.util.LinkedList;

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
* */
public class Test322_2 {
    public int coinChange(int[] coins, int amount) {
        if (coins.length < 1 || amount <= 0){ return 0; }
        Coin[] cs = new Coin[coins.length];
        int minCoin = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i ++){
            cs[i] = new Coin(amount - coins[i] + 1);
            minCoin = Math.min(minCoin, coins[i]);
        }

        if (minCoin > amount){
            return -1;
        }

        int dp = -1;
        for (int i = 1; i <= amount; i ++){
            dp = Integer.MAX_VALUE;
//            System.out.println("i = " + i);

            for (int j = 0; j < cs.length; j ++){
//                System.out.println("\tj = " + j);
                if (cs[j].index + coins[j] < i){
                    cs[j].pop();
                }
                if (cs[j].index + coins[j] == i && cs[j].value() != Integer.MAX_VALUE){
                    dp = Math.min(cs[j].value() + 1, dp);
                }
            }

            for (int j = 0; j < cs.length; j ++){
                cs[j].add(dp);
                if (cs[j].index + coins[j] == i && cs[j].value() + 1 == dp){
                    cs[j].pop();
                }
            }
//            System.out.println("\t" + dp);
        }
        return dp == Integer.MAX_VALUE ? -1 : dp;
    }

    private static class Coin{
        LinkedList<Integer> list;
        private int limit;
        private int num = 0;
        private int index = 0;
        public Coin(int limit){
            this.limit = limit;
            list = new LinkedList<>();
            list.add(0);
        }

        public Integer value(){
            return list.getFirst();
        }

        public Integer pop(){
            index ++;
            return list.removeFirst();
        }

        public void add(Integer in){
            if (num < limit){
                list.addLast(in);
                num ++;
            }
        }
    }

    public static void main(String[] args) {
        Test322_2 t = new Test322_2();
        int[] nums = {1, 2, 5};
        int amount = 11;
        System.out.println(t.coinChange(nums, amount));
    }
}
