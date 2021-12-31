package test;

import java.util.Arrays;

public class Test1798 {
    public int getMaximumConsecutive(int[] coins) {

        Arrays.sort(coins);
        int n = 1;
        for (int index = 0; index < coins.length;) {
            if (coins[index] <= n) {
                n += coins[index];
                index ++;
            } else {
                break;
            }
        }
        return n;
    }
}
