package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

public class Test587 {

    public int[][] outerTrees(int[][] trees) {
        final int n = trees.length;
        Node[] mytree = new Node[n];
        for (int i = 0; i < n; i ++) {
            mytree[i] = new Node(trees[i]);
        }

        int l = 0x7fff, r = -1, t = -1, b = 0x7fff;
        for (int i = 0; i < n; i ++) {
            l = Math.min(l, trees[i][0]);
            r = Math.max(r, trees[i][0]);
            t = Math.max(t, trees[i][1]);
            b = Math.min(b, trees[i][1]);
        }

        HashSet<Node> vertex = new HashSet<>();

        for (int i = 0; i < n; i ++) {
            if (l == trees[i][0] || r == trees[i][0] || t == trees[i][1] || b == trees[i][1]) {
                vertex.add(mytree[i]);
            }
        }

        Integer[] indexes = new Integer[n - vertex.size()];
        int[][] summary = new int[n - vertex.size()][];
        for (int i = 0; i < n; i ++) {
            if (vertex.contains(mytree[i])) continue;
            for (Node node: vertex) {
                summary[i][0]  += node.xy[0] - trees[i][0];
                summary[i][1]  += node.xy[1] - trees[i][1];
            }
        }

        Comparator<Integer> cmp = (i0, i1) -> {
            int mag0 = summary[i0][0] * summary[i0][0] + summary[i0][1] * summary[i0][1];
            int mag1 = summary[i1][0] * summary[i1][0] + summary[i1][1] * summary[i1][1];
            if (mag0 < mag1) return 1;
            if (mag0 > mag1) return -1;
            return 0;
        };

        Arrays.sort(indexes, cmp);



    }

    public static class Node {
        public int[] xy;
        public Node(int[] xy) {
            this.xy = xy;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(xy);
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node)obj;
            return obj == this || node.xy[0] == xy[0] && node.xy[1] == xy[1];
        }
    }
}
