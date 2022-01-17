package test;

import java.util.*;

public class Test354_3 {
    public int maxEnvelopes(int[][] envelopes) {
        TreeMap<int[], Integer> tree = new TreeMap<>((o0, o1)->{
            int index = 1;
            if (o0[index] < o1[index]) return -1;
            if (o0[index] > o1[index]) return 1;
            if (o0[1 - index] < o1[1 - index]) return -1;
            if (o0[1 - index] > o1[1 - index]) return 1;
            return 0;
        });


        Arrays.sort(envelopes, (o0, o1)-> {
            int index = 0;
            if (o0[index] < o1[index]) return -1;
            if (o0[index] > o1[index]) return 1;
            if (o0[1 - index] < o1[1 - index]) return -1;
            if (o0[1 - index] > o1[1 - index]) return 1;
            return 0;
        });

        int result = 0;
        for (int i = 0; i < envelopes.length; i ++) {
            SortedMap<int[], Integer> head = tree.headMap(envelopes[i]);
            int level = 0;
            for (Map.Entry<int[], Integer> ent : head.entrySet()) {
                int[] key = ent.getKey();
                if (key[0] >= envelopes[i][0] || key[1] >= envelopes[i][1]) continue;
                Integer v = ent.getValue();
                v = null == v ? 0 : v;
//                System.out.println("next = [" + key[0] + "," + key[1] + "] = " + v);
                level = Math.max(level, v);
            }
            tree.put(envelopes[i], level + 1);
            result = Math.max(result, level + 1);
//            System.out.println("cur = [" + envelopes[i][0] + "," + envelopes[i][1] + "] = " + level);
//            System.out.println("-----");
        }
        return result;
    }
}
