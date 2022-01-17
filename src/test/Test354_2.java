package test;

import java.util.Arrays;

public class Test354_2 {
    public int maxEnvelopes(int[][] envelopes) {
        Integer[][] indexes0 = new Integer[2][envelopes.length];
        Integer[][] indexes1 = new Integer[2][envelopes.length];
        prepare(indexes0, indexes1, envelopes);

        int[] cache = new int[envelopes.length];
        int result = 0;
        for (int i = 0; i < indexes0[0].length; i ++) {
            /*在宽这个维度上从小到大找*/
            int wIndex = indexes0[0][i];
            int count = 0;
            for (int j = indexes1[1][wIndex] - 1; j >= 0 && count <= i; j --) {
                int hIndex = indexes1[0][j];
                if (envelopes[hIndex][1] >= envelopes[wIndex][1]
                        || envelopes[hIndex][0] >= envelopes[wIndex][0]) continue;
                cache[wIndex] = Math.max(cache[hIndex] + 1, cache[wIndex]);
                if (envelopes[hIndex][0] < envelopes[wIndex][0]) count++;
            }
            result = Math.max(cache[wIndex], result);
        }
        return result + 1;

    }

    public void prepare(Integer[][] indexes0, Integer[][] indexes1, int[][] envelopes) {
        for (int i = 0; i < indexes0[0].length; i ++) {
            indexes0[0][i] = i;
            indexes1[0][i] = i;
        }

        Arrays.sort(indexes0[0], (o0, o1)->{
            int index = 0;
            if (envelopes[o0][index] < envelopes[o1][index]) return -1;
            if (envelopes[o0][index] > envelopes[o1][index]) return 1;
            if (envelopes[o0][1 - index] < envelopes[o1][1 - index]) return -1;
            if (envelopes[o0][1 - index] > envelopes[o1][1 - index]) return 1;
            return 0;
        });

        Arrays.sort(indexes1[0], (o0, o1)->{
            int index = 1;
//            if (envelopes[o0][1] < envelopes[o1][1]) return -1;
//            if (envelopes[o0][1] > envelopes[o1][1]) return 1;
            if (envelopes[o0][index] < envelopes[o1][index]) return -1;
            if (envelopes[o0][index] > envelopes[o1][index]) return 1;
            if (envelopes[o0][1 - index] < envelopes[o1][1 - index]) return -1;
            if (envelopes[o0][1 - index] > envelopes[o1][1 - index]) return 1;

            return 0;
        });

        for (int i = 0; i < indexes0[0].length; i ++) {
            indexes0[1][indexes0[0][i]] = i;
            indexes1[1][indexes1[0][i]] = i;
        }
    }

    public static void main(String[] args) {
        int[][] envs = new int[][]{{5,4},{6,4},{6,7},{2,3},{5,3},{4,2},{3,1}};
//        int[][] envs = new int[][]{{5,4},{6,4},{6,7},{2,3},{2,1},{5,5},{7,9}};
//        int[][] envs = new int[][]{{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
        
        Test354_2 t = new Test354_2();
        int result = t.maxEnvelopes(envs);
//        int result = 0;

        Test354_3 t2 = new Test354_3();
        int result2 = t2.maxEnvelopes(envs);

        System.out.println(result + ", " + result2);
    }
}
