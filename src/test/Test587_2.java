package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Test587_2 {
    public int[][] outerTrees(int[][] trees) {
        final int n = trees.length;
        double mX = 0, mY = 0;
        for (int i = 0; i < n; i ++) {
            mX += trees[i][0];
            mY += trees[i][1];
        }
        mX /= n;
        mY /= n;

        double[][] trees2 = new double[n][];
        double[][] thetas = new double[n][];
        double maxSqDis = 0;
        int maxSqDisIndex = -1;
        double maxSqTheta = 0;
        for (int i = 0; i < n; i ++) {
            double x = trees[i][0] - mX, y = trees[i][1] - mY;
            trees2[i] = new double[] {x, y, i};
            double sqDis = x * x + y * y;
            double dis = Math.sqrt(sqDis);
            double t;
            if (dis <= 1e-6)
                t = 0;
            else
                t = y >= 0 ? Math.acos(x / dis) : Math.PI * 2 - Math.acos(x / dis);
            thetas[i] = new double[]{t, i};
            if (sqDis > maxSqDis) {
                maxSqDis = sqDis;
                maxSqDisIndex = i;
                maxSqTheta = t;
            }
        }

        HashSet<Integer> vertexIndexes = new HashSet<>();
        vertexIndexes.add(maxSqDisIndex);

        Comparator<double[]> cmp = (o0, o1)-> {
            if (o0[0] < o1[0]) return -1;
            if (o0[0] > o1[0]) return 1;
            return 0;
        };

        Arrays.sort(thetas, cmp);

        int firstIndex = Arrays.binarySearch(thetas, new double[]{maxSqTheta}, cmp);
        int index = firstIndex;
        for (;index < firstIndex + n; ) {
            for (int i = index + 1; i < firstIndex + n; i ++) {
                int ii = i % n;
//                if (thetas[ii] )

            }
        }


        return null;

    }
}
