package other;

import java.util.ArrayList;

public class ShortestPath {
    public int shortestPath(int[][] matrix, int from, int to){
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i ++){
            for (int j = 0; j < matrix[i].length; j ++ ){
                if (matrix[i][j] != Integer.MIN_VALUE){
                    list.add(new int[]{i, j});
                }
            }
        }
        int[][] edge = list.toArray(new int[0][]);
        int[] parent = new int[matrix.length];
        parent[from] = -1;
        for (int i = 0; i < matrix.length - 1; i ++){
            for (int j = 0; j < edge.length; j ++){
                if (matrix[from][edge[j][0]] != Integer.MIN_VALUE){
                    if (matrix[from][edge[j][1]] > matrix[from][edge[j][0]] + matrix[edge[j][0]][edge[j][1]]) {
                        matrix[from][edge[j][1]] = matrix[from][edge[j][0]] + matrix[edge[j][0]][edge[j][1]];
                        parent[edge[j][1]] = edge[j][0];
                    }
                }
            }
        }
        int p = to;
        int p2 = to;
        ArrayList<Integer> path = new ArrayList<>();
        path.add(to);
        while (parent[p] != -1){
            p = parent[p];
            if (parent[p2] != -1){
                p2 = parent[p2];
                if (parent[p2] != -1){
                    p2 = parent[p2];
                    if (p2 == p){
                        System.out.println("wrong path " + path);
                        return Integer.MIN_VALUE;
                    }
                }
            }
            path.add(p);
        }
        System.out.println("from:" + from + ", to:" + to + ", path = " + path);
        return matrix[from][to];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 2, 1, 3, 100, 9},
                {1, 0, 1, +1, 8, 10},
                {10, 2, 0, 5, +2, 9},
                {23, 9, 6, 0, +2, +3},
                {9, 18, 16, 9, 0, 1},
                {2, 10, 8, 2, 11, 0}
        };
        matrix = new int[][]{
                {0, 10, 6, 9},
                {-1, 0, 7, 8},
                {9, -6, 0, 4},
                {-5, -2, 3, 0}
        };
//        matrix = new int[][]{
//                {0, 2, 8, 1, 0, 0, 0, 0},
//                {2, 0, 6, 0, 1, 0, 0, 0},
//                {8, 6, 0, 7, 4, 2, 2, 0},
//                {1, 7, 0, 0, 0, 0, 9, 0},
//                {0, 1, 4, 0, 0, 3, 0, 9},
//                {0, 2, 0, 0, 3, 0, 4, 6},
//                {0, 0, 2, 9, 0, 4, 0, 2},
//                {0, 0, 0, 0, 9, 6, 2, 0}
//        };

        ShortestPath path = new ShortestPath();
        System.out.println(path.shortestPath(matrix, 0, matrix[0].length - 1));

//        for (int i = 0; i < matrix.length; i ++){
//            for (int j = 0; j < matrix.length; j ++){
//                System.out.println(path.shortestPath(matrix, i, j));
//            }
//        }
    }
}
