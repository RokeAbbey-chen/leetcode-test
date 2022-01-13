package test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Test354_3 {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o0, o1)-> {
            if (o0[0] < o1[0]) return -1;
            if (o0[0] > o1[0]) return 1;
            if (o0[1] < o1[1]) return -1;
            if (o0[1] > o1[1]) return 1;
            return 0;
        });
//        PriorityQueue<int[]> queue = new PriorityQueue<>();
        return 0;
    }
}
