package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Test391 {
    public boolean isRectangleCover(int[][] rectangles) {
        // b l t r
        Map<Integer, ArrayList<int[]>>[] bltr = new Map[]{new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>()};
        int b = Integer.MAX_VALUE, l = Integer.MAX_VALUE, t = Integer.MIN_VALUE, r = Integer.MIN_VALUE;
        int[][] hv = new int[4][];
        for (int i = 0; i < rectangles.length; i ++) {
            int[] rec = rectangles[i];
            int[] hor = new int[]{rec[1], rec[3]}; // left to right
            int[] ver = new int[]{rec[0], rec[2]}; // bottom to top
            b = Math.min(b, rec[0]);
            l = Math.min(l, rec[1]);
            t = Math.max(t, rec[2]);
            r = Math.max(r, rec[3]);
            hv[0] = hor; hv[1] = ver; hv[2] = hor; hv[3] = ver;
            for (int j = 0; j < 4; j ++) {
                Map<Integer, ArrayList<int[]>> map = bltr[j];
                ArrayList<int[]> arr = map.get(rec[j]);
                if (null == arr) {
                    arr = new ArrayList<>();
                    map.put(rec[j], arr);
                }
                arr.add(hv[j]);
            }
        }

        for (int i = 0; i < bltr.length; i ++) {
            for (ArrayList<int[]> list: bltr[i].values()) {
                Collections.sort(list, (o0, o1) -> {
                    if (o0[0] != o1[0]) return o0[1] - o1[1];
                    return o0[0] - o1[0];
                });
            }
        }

        return isBoundingRect(b, l, t, r, bltr) && isNoCover(bltr);
    }

    public static boolean checkConnect(ArrayList<int[]> edges, boolean perfect) {
        int size = edges.size();
        int[] lastEdge = edges.get(0);
        for (int i = 1; i < size; i ++) {
            int[] edge = edges.get(i);
            if (!(perfect && edge[0] == lastEdge[1] || !perfect && edge[0] >= lastEdge[1])) return false;
            lastEdge = edge;
        }
        return true;
    }

    public static boolean match(ArrayList<int[]> edges0, ArrayList<int[]> edges1, boolean perfect) {
        if (perfect)
            return checkConnect(edges0, true) && checkConnect(edges1, true) // 完全无交集相连
                    && edges0.get(0)[0] == edges1.get(0)[0] // 起点贴合
                    && edges0.get(edges0.size() - 1)[1] == edges1.get(edges1.size() - 1)[1]; // 终点贴合
        else {
            int start0 = 0, size0 = edges0.size();
            int start1 = 0, size1 = edges1.size();
            while (start0 < size0 && start1 < size1) {
                int end0 = findSegment(edges0, start0);
                if (end0 < 0) return false;
                int end1 = findSegment(edges1, start1);
                if (end1 < 0) return false;
                if (edges0.get(start0)[0] != edges1.get(start1)[0]
                        || edges0.get(end0 - 1)[1] != edges1.get(end1 - 1)[1])
                    return false;
                start0 = end0;
                start1 = end1;
            }
            return true;
        }
    }

    public static int findSegment(ArrayList<int[]> edges, int start) {
        int i = start + 1;
        int size0 = edges.size();
        for (; i < size0; i++) {
            int[] ed0 = edges.get(i - 1);
            int[] ed1 = edges.get(i);
            if (ed0[1] < ed1[0]) break;
            if (ed0[1] > ed1[0]) return - (i + 1);
        }
        return i;

    }

    public static boolean isBoundingRect(int b, int l, int t, int r, Map<Integer, ArrayList<int[]>>[] bltr) {
        ArrayList<int[]> bottomEdges = bltr[0].get(b);
        ArrayList<int[]> topEdges = bltr[2].get(t);
        ArrayList<int[]> leftEdges = bltr[1].get(l);
        ArrayList<int[]> rightEdges = bltr[3].get(r);

        return match(bottomEdges, topEdges, true) && match(leftEdges, rightEdges, true);
    }

    public static boolean isNoCover(Map<Integer, ArrayList<int[]>>[] bltr) {
        for (int i = 0; i < 4; i ++) {
            int count = 0;
            for (Map.Entry<Integer, ArrayList<int[]>> ent : bltr[i].entrySet()) {
                Integer bottomKey = ent.getKey();
                ArrayList<int[]> edges0 = ent.getValue();
                ArrayList<int[]> edges1 = bltr[(i + 2) & 3].get(bottomKey);
                if (null == edges1) {
                    if (0 == count) {count++;continue;}// 找到外接bottom
                    else return false; //
                }
                if (i < 2 && !match(edges0, edges1, false)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Test391 t = new Test391();
//        int[][] recs = {{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}};
//        int[][] recs = {{1,1,2,3},{1,3,2,4},{3,1,4,2},{3,2,4,4}};
        int[][] recs = {{1,1,3,3},{3,1,4,2},{1,3,2,4},{2,2,4,4}};
//        int[][] recs = {{1,1,2,3}};
//        int[][] recs = {{10, 80,}}
        boolean result = t.isRectangleCover(recs);
        System.out.println(result);
    }
}
