package test;

import java.util.HashMap;
import java.util.Objects;

public class Test149 {
    static private class Line {
        public double slope;
        public double gap;
        public Line(double slope,  double gap) {
            this.slope = slope;
            this.gap = gap;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return Double.compare(line.slope, slope) == 0 && Double.compare(line.gap, gap) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(slope, gap);
        }

        @Override
        public String toString() {
            return "Line{" +
                    "slope=" + slope +
                    ", gap=" + gap +
                    '}';
        }
    }
    public int maxPoints(int[][] points) {
        HashMap<Line, Integer> counts = new HashMap<>();
        int maxCount = 0;
        for (int i = 0; i < points.length; i ++) {
            for (int j = i + 1; j < points.length; j ++) {
//                i = 29; j = 57;
                System.out.println("i = " + i + ", j = " + j);
                int d0 = points[j][0] - points[i][0];
                int d1 = points[j][1] - points[i][1];
                if (Math.abs(d0) < 1e-8) d1 = Math.abs(d1);
                double dis = calDistance(points[i], points[j]); // 正无穷 斜率与负无穷没区别。
                dis = Math.round(dis * 1e5) / 1e5;
                Line line = new Line(d1 * 1.0 / d0, dis);
                Integer c = counts.get(line);
                if (null == c) c = 0;
                c ++;
                counts.put(line, c);
                maxCount = Math.max(maxCount, c);
                System.out.println("point[i] = (" + points[i][0] + ", " + points[i][1] + "), points[j] = (" + points[j][0] + ", " + points[j][1] + ").");
                System.out.println("line:" + line + ", c = " + c + ", maxC = " + maxCount);
                System.out.println("--------------");

            }
        }
        return findFactorCount(maxCount << 1);
    }



    public int findFactorCount(int count) {
        int i = 0;
        for (;i * (i + 1) < count; i ++);
        return i + 1;
    }


    public double calDistance(int[] point0, int[] point1) {
        double[] vector0 = {point0[0], point0[1]};
        double[] vector1 = {point1[0] - point0[0], point1[1] - point0[1]};
        double[][] rt = {{-vector1[1], vector1[0]},
                         {vector1[1], -vector1[0]}};
        double[] mul = {vectorMul(rt[0], vector0), vectorMul(rt[1], vector0)};
        int index = mul[1] > 0 ? 1: 0;
        int sig;
        if (rt[index][0] > 0 || rt[index][0] == 0 && rt[index][1] > 0) sig = 1;
        else sig = -1;
        return sig * mul[index] / modulus(rt[index]);
    }

    private double modulus(double[] point) {
        return Math.sqrt(point[0] * point[0] + point[1] * point[1]);
    }

    private double vectorMul(double[] v0, double[] v1) {
        return v0[0] * v1[0] + v0[1] * v1[1];
    }

    public static void main1(String[] args) {
        Test149 t = new Test149();
        int[][] points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}, {7, 4}, {9, 5}};
//        points = new int[][]{{0, 0}};
        points = new int[][]{{7,3},{19,19},{-16,3},{13,17},{-18,1},{-18,-17},{13,-3},{3,7},{-11,12},{7,19},{19,-12},{20,-18},{-16,-15},{-10,-15},{-16,-18},{-14,-1},{18,10},{-13,8},{7,-5},{-4,-9},{-11,2},{-9,-9},{-5,-16},{10,14},{-3,4},{1,-20},{2,16},{0,14},{-14,5},{15,-11},{3,11},{11,-10},{-1,-7},{16,7},{1,-11},{-8,-3},{1,-6},{19,7},{3,6},{-1,-2},{7,-3},{-6,-8},{7,1},{-15,12},{-17,9},{19,-9},{1,0},{9,-10},{6,20},{-12,-4},{-16,-17},{14,3},{0,-1},{-18,9},{-15,15},{-3,-15},{-5,20},{15,-14},{9,-17},{10,-14},{-7,-11},{14,9},{1,-1},{15,12},{-5,-1},{-17,-5},{15,-2},{-12,11},{19,-18},{8,7},{-5,-3},{-17,-1},{-18,13},{15,-3},{4,18},{-14,-15},{15,8},{-18,-12},{-15,19},{-9,16},{-9,14},{-12,-14},{-2,-20},{-3,-13},{10,-7},{-2,-10},{9,10},{-1,7},{-17,-6},{-15,20},{5,-17},{6,-6},{-11,-8}};
//        points = new int[][]{{-9,-651},{-4,-4},{-8,-582},{9,591},{-3,3}};
//        points.hashCode()
        int result = t.maxPoints(points);
        System.out.println(result);

        int[] p = {0};
        int[] p1 = {0};

//        Integer i = 12345;
//        Integer i1 = 12345;
        System.out.println("p:" + p.hashCode() + ", p1:" + p1.hashCode());

    }

    public static void main(String[] args) {
        main1(args);
    }
}
