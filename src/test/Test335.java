package test;

public class Test335 {
    public boolean isSelfCrossing(int[] distance) {
        if (distance.length <= 3) return false;
        final int N = 16;
        int[][] points = new int[N][2]; // x, y
        points[0][0] = 0; points[0][1] = 0;

        int[] factorX = {0, -1, 0, 1};
        int[] factorY = {1, 0, -1, 0};
        for (int i = 0; i < distance.length; i ++) {
            points[(i + 1) % N][0] = points[i % N][0] + factorX[i & 3] * distance[i];
            points[(i + 1) % N][1] = points[i % N][1] + factorY[i & 3] * distance[i];

            for (int offset = 1; offset >= -1; offset --) {
                if (i + offset - 4 >= 0
                        && isCross(points[i % N], points[(i + 1) % N], points[(i + offset - 4) % N], points[(i + 1 + offset - 4) % N])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCross(int[] p00, int[] p01, int[] p10, int[] p11) {
        int l = min4(p00[0], p01[0], p10[0], p11[0]);
        int r = max4(p00[0], p01[0], p10[0], p11[0]);
        int t = min4(p00[1], p01[1], p10[1], p11[1]);
        int b = max4(p00[1], p01[1], p10[1], p11[1]);

        /**
         * 外接矩形各边与两条边的长度都一样。说明相交
         */
        if (l != r && t != b) {
            return (r - l) <= Math.max(Math.abs(p00[0] - p01[0]), Math.abs(p10[0] - p11[0]))
                    && (b - t) <= Math.max(Math.abs(p00[1] - p01[1]), Math.abs(p10[1] - p11[1]));
        }

        /**
         * 此时是平行线段有交集的情况
         */
        if (l == r) {
            return b - t <= Math.abs(p00[1] - p01[1]) + Math.abs(p10[1] - p11[1]);
        }
        return r - l <= Math.abs(p00[0] - p01[0]) + Math.abs(p10[0] - p11[0]);
    }

    public static int min4(int a, int b, int c, int d) {
        int result = a;
        result = Math.min(result, b);
        result = Math.min(result, c);
        result = Math.min(result, d);
        return result;
    }

    public static int max4(int a, int b, int c, int d) {
        int result = a;
        result = Math.max(result, b);
        result = Math.max(result, c);
        result = Math.max(result, d);
        return result;
    }

    public static void main(String[] args) {
        int[] distance;
        distance = new int[]{2,1,1,2};
        distance = new int[]{1,2,3,4};
        distance = new int[]{1,1,1,1};
        distance = new int[]{2,2,4,2,1};
        Test335 t = new Test335();
        boolean result = t.isSelfCrossing(distance);
        System.out.println(result);
    }
}
