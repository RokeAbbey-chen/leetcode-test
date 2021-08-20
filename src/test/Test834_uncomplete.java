package test;

import java.util.*;

public class Test834_uncomplete {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (edges.length < 1) { return new int[]{0}; }

        HashMap<Integer, Node> nodes = new HashMap<>();
        Integer[] indexes = new Integer[edges.length];
        for (int i = 0; i < indexes.length; i ++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (o1, o2) -> {
            int min0 = Math.min(edges[o1][0], edges[o1][1]);
            int min1 = Math.min(edges[o2][0], edges[o2][1]);
            if (min0 < min1) { return -1; }
            else if (min0 > min1) { return 1; }
            return 0;
        });
        nodes.put(edges[indexes[0]][0], new Node(-1, 1, 0, 2));
        nodes.put(edges[indexes[0]][1], new Node(edges[indexes[0]][0], 1, 1, 2));

        int[] path = new int[n];
        int[] dis = new int[n];
        for (int i = 1; i < edges.length; i++) {
            if (nodes.containsKey(edges[indexes[i]][1])) {
                swapOne(edges[indexes[i]]);
//                j = 1;
            }
            int parent = edges[indexes[i]][0];
            int me = edges[indexes[i]][1];
            int len = getPath(nodes, path, parent);
            promise(nodes, path, len);
            add(nodes, path, len, me);
        }

        HashSet<Integer> notLeaves = new HashSet<>();
        for (int i = edges.length - 1; i >= 0; i--) {
            if (notLeaves.contains(edges[indexes[i]][1])) {
                notLeaves.add(edges[indexes[i]][0]);
                continue;
            }
            int len = getPath(nodes, path, edges[indexes[i]][1]);
            promise(nodes, path, len);
            notLeaves.add(edges[indexes[i]][0]);
        }

        for (int i = 0; i < edges.length; i++) {
            dis[edges[i][0]] = nodes.get(edges[i][0]).dis;
            dis[edges[i][1]] = nodes.get(edges[i][1]).dis;
        }
//        dis[edges[0][0]] = nodes.get(edges[0][0]).dis;
        return dis;
    }


    public void swapOne(int[] e) {
        int tmp = e[0];
        e[0] = e[1];
        e[1] = tmp;
    }

    public int getPath(HashMap<Integer, Node> nodes, int[] path, int me) {
        int len = 0;
        for (Node meNode = nodes.get(me); meNode != null; meNode = nodes.get(me)) {
            path[len] = me;
            me = meNode.parent;
            len++;
        }
        return len;
    }

    public void promise(HashMap<Integer, Node> nodes, int[] path, int len) {
        Node parent = nodes.get(path[len - 1]);
        for (int i = len - 2; i >= 0; i--) {
            Node me = nodes.get(path[i]);
            int addedCount = parent.count - me.count;
            int adddeDis = parent.dis - me.parentLastDis;
            me.count = parent.count;
            me.dis += adddeDis + addedCount;
            parent = me;
        }
    }

    public void add(HashMap<Integer, Node> nodes, int[] path, int len, int me) {
        Node parent = nodes.get(path[0]);
        int oldParentDis = parent.dis;
        int oldParentCount = parent.count;
        for (int i = len - 1; i >= 0; i--) {
            Node ancestor = nodes.get(path[i]);
            ancestor.count++;
            ancestor.dis += i + 1;
            if (i < len - 1) {
                ancestor.parentLastDis = parent.dis;
            }
            parent = ancestor;
        }
        Node node = new Node(path[0], oldParentDis + oldParentCount, parent.dis, nodes.size() + 1);
        nodes.put(me, node);
    }

    static class Node {
        int parent;
        /**
         * 记录上回有节点加入到自己的字数中之后，自己的dis总和.
         */
        int dis;
        /**
         * 记录上回有节点加入到自己的子树中之后，parent的dis总和。
         */
        int parentLastDis;

        /**
         * 记录算上自己在内，当前树中一共有多少节点。
         */
        int count;

        public Node(int parent, int dis, int parentLastDis, int count) {
            this.parent = parent;
            this.dis = dis;
            this.parentLastDis = parentLastDis;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(count:" + count + ", dis:" + dis + ", pDis:" + parentLastDis + ")";
        }

    }

    public static void main(String[] args) {
        Test834_uncomplete t = new Test834_uncomplete();
//        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
//        int n = 6;
//        int[][] edges = {{0, 1}, {0, 2}};
//        int n = 3;
//        int[][] edges = {{0, 1}, {0, 2}, {0, 3}};
//        int n = 4;
//        int n = 3;
//        int[][] edges = {{2, 0}, {1, 0}};
//        int n = 3;
//        int[][] edges = {{2, 1}, {0, 2}};
//        int n = 9;
//        int[][] edges = {{1,0},{2,0}, {0,3}, {2,4}, {4,5},{3,6},{8,7},{7,6}};

        int n = 4;
        int[][] edges = {{1, 2}, {3, 2}, {3, 0}};


        int[] result = t.sumOfDistancesInTree(n, edges);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
