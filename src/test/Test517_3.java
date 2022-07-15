package test;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test517_3 {
    public int findMinMoves(int[] machines) {
        int sum = 0;
        int n = machines.length;
        int[] mac = machines;
        for (int i = 0; i < n; i ++) sum += mac[i];
        int avg = sum / n;
        if (avg * n != sum) return -1;

        int k = 0;
        for (; k < n && mac[k] == avg; k ++);

        if (n == k) return 0;

        System.out.println("avg = " + avg);


    }

    public int fill(int[] mac, int avg, int macStart) {
        int n = mac.length;
        LinkedList<int[]> queue = new LinkedList<>();
        int needFill = 0, needMove = 0;

        boolean bFillOrMove = mac[macStart] < avg;

        int sumFill = 0, sumMove = 0;
        for (int i = macStart; i <= n; i ++) {
            if (needFill > 0 && (i >= n || mac[i] > avg)) {
                queue.add(new int[] {needFill, i - 1});
                sumFill += needFill;
                needFill = 0;
            }

            if (needMove > 0 && (i >= n || mac[i] < avg)) {
                queue.add(new int[] {needMove, i - 1});
                sumMove += needMove;
                needMove = 0;
            }

            if (bFillOrMove && sumMove >= sumFill || !bFillOrMove && sumFill >= sumMove) {
                solve(mac, queue, bFillOrMove);
            }
            if (i < n) {
                int stepFill = Math.max(0, avg - mac[i]);
                int stepMove = Math.max(0, mac[i] - avg);
                needFill += stepFill;
                needMove += stepMove;
            }

        }
    }


    public int[] solve(int[] mac, LinkedList<int[]> queue, boolean flag) {
        int[] result = {0, 0, 0};

        LinkedList<int[]> queue2 = new LinkedList<>();
        int maxMinTimes = 0;
        int minTimes = Integer.MAX_VALUE;
        while (queue.size() >= 2) {
            int[] node1 = queue.removeLast();
            int[] node0 = queue.removeLast();
            minTimes = Math.min(Math.min(node0[0], node1[0]), minTimes);



        }
    }

































}
