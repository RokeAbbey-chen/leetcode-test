package xiaobai;

import java.util.TreeSet;

public class Test10 {
    public static void main(String[] args) {
        Test10 t = new Test10();
        // 1
        int[] red = {1, 2};
        int[] blue = {3, 3};

        red = new int[]{1, 101, 501};
        blue = new int[]{3, 2, 3};
        System.out.println(t.solve(red, blue));
    }

    public int solve(int[] red, int[] blue){
        TreeSet<Node> treeSet = new TreeSet<>();
        int[] root = new int[red.length];
        int result = 0;
        for (int i = 0; i < red.length; i ++){
            for (int j = i + 1; j < blue.length; j ++){
                treeSet.add(new Node(i, j, Math.min(red[i] ^ blue[j], blue[i] ^ red[j])));
            }
            root[i] = i;
        }

        for (Node node : treeSet){
            if (!inTheSameTree(node.x, node.y, root)){
                result += node.w;
            }
        }

        return result;
    }

    private boolean inTheSameTree(int w, int v, int[] root){
        w = findRoot(w, root);
        v = findRoot(v, root);
        if (w != v){
            root[w] = root[v];
            return false;
        }
        return true;
    }

    private int findRoot(int x, int[] root){
        return x == root[x] ? x : (root[x] = findRoot(root[x], root));
    }
    private static class Node implements Comparable<Node>{
        public int x;
        public int y;
        public int w;

        public Node(){

        }

        public Node(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            if (this.w > o.w){ return 1; }
            else if (this.w < o.w){ return -1; }
            return 0;
        }
    }
}
