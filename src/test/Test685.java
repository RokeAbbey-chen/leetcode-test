package test;

public class Test685 {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;
        int[][] parents = new int[len + 1][3];
        int node = -1;
        for (int i = 0; i < len; i ++) {
            int v0 = edges[i][0], v1 = edges[i][1];
            parents[v1][parents[v1][2] ++] = v0;
            if(parents[v1][2] >= 2) {
                node = v1;
            }
        }
        if (node >= 0) {
            int p1 = node;
            int p2 = node;
            for (int i = 0;(0 == i || p1 != p2) && parents[p1][2] >= 1 && parents[p2][2] >= 1; i ++) {
                p1 = parents[p1][0];
                p2 = parents[p2][0];
                p2 = parents[p2][0];
            }
            if (p1 == p2) {
                return new int[]{parents[node][0], node};
            }
            return new int[]{parents[node][1], node};


        } else {


            int p1 = parents[1][0];
            int p2 = parents[p1][0];
            p2 = parents[p2][0];
            while (p1 != p2) {
                p1 = parents[p1][0];
                p2 = parents[p2][0];
                p2 = parents[p2][0];
            }
            boolean[] inCircle = new boolean[len + 1];
            for (int i = 0; i <= 0 || p1 != p2; i++) {
                inCircle[p1] = true;
                p1 = parents[p1][0];
                p2 = parents[p2][0];
                p2 = parents[p2][0];
            }

            for (int i = len - 1; i >= 0; i--) {
                int v0 = edges[i][0];
                int v1 = edges[i][1];
                if (inCircle[v0] && inCircle[v1])
                    return edges[i];
            }
        }
        return null;
    }


    public void func() {
        final int N = 103;
        int[] arr = new int[N];
        for (int i = 0; i < N; i ++) {
            arr[i] = i + 1;
        }
        arr[N - 1] = 89;

        int p = 0, p2 = 0;
        for (int i = 0; i < 10000; i ++) {
            p = arr[p];
            p2 = arr[p2];
//            if (p2 == p) {
//                System.out.println("0 i = " + i + ", p = " + p + ", p2 = " + p2);
//            }
            p2 = arr[p2];
            if (p2 == p) {
                System.out.println("1 i = " + i + ", p = " + p + ", p2 = " + p2);
//                break;
            }

        }
    }
    public static void main(String[] args) {
        Test685 test = new Test685();
//        int[][] edges = {
//                {1, 2},
//                {2, 3},
//                {3, 4},
//                {4, 1}
//        };
        int[][] edges = {{1,2},{2,3},{3,4},{4,1},{1,5}};
//        int[][] edges = {{2,1},{3,1},{4,2},{1,4}};
//        int[][] edges = {{4,2},{1,5},{5,2},{5,3},{2,4}};
//        int[][] edges = {{1, 3}, {3, 4}, {4, 2}, {2, 4}};
//        int[][] edges = {{1, 3}, {3, 4}, {4, 2}, {2, 3}};

        int[] result = test.findRedundantDirectedConnection(edges);
        System.out.println(result[0] + ", " + result[1]);
    }

    public static void main1(String[] args) {
        Test685 test = new Test685();
        test.func();
    }
}
