package test;

import java.util.Arrays;
import java.util.Comparator;

/*
* You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
*
* 失败
* */
public class Test354 {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]){
                    return o2[1] - o1[1];
                }
                else {
                    return o1[0] - o2[0];
                }
            }
        });

        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int i = 0; i < envelopes.length; i ++){
            int index = Arrays.binarySearch(dp, 0, len, envelopes[i][1]);
            if (index < 0){
                index = - index - 1;
            }
            dp[index] = envelopes[i][1];
            if (len == index){
                len ++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        Test354 t = new Test354();
        int[][] envs = {{5,4},{6,4},{6,7},{2,3}};
//        [[30,50],[12,2],[3,4],[12,15]]
        int result = t.maxEnvelopes(envs);
        System.out.println(result);
//        int[] ar = {0, 1, 2, 3, 5};
//        System.out.println(Arrays.binarySearch(ar, 4));
    }
}
