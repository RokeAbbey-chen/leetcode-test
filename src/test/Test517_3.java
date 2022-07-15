package test;

import java.util.*;

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

        ArrayList<Integer> queue = new ArrayList<>();
        int sum0 = 0, sum1 = 0;
        int sum00 = 0, sum11 = 0;
        int result = 0;

        int exclusive = 0;
        int rest0 = 0;
        for (int i = 0; i < n; i ++) {

            sum00 += Math.max(0, avg - mac[i]);
            sum11 += Math.max(0, mac[i] - avg);

            System.out.println("sum00 = " + sum00 + ", sum11 = " + sum11);
            if (sum0 > sum1 && sum0 + sum00 <= sum1 + sum11) {
                System.out.println("1. sum0 = " + sum0 + ", sum1 = " + sum1);
                int rest = sum1 + sum11 - sum0 - sum00;
                exclusive = mac[i] - rest - avg;
                queue.add(exclusive);
                result = Math.max(result, solve(queue));
                sum0 = sum00 = sum1 = 0;
                sum11 = rest;
                rest0 = rest;
            } else if (sum1 > sum0 && sum1 + sum11 <= sum0 + sum00) {
                System.out.println("2. sum0 = " + sum0 + ", sum1 = " + sum1);
                int rest = sum0 + sum00 - sum1 - sum11;
                queue.add(mac[i] - rest - avg);
                int solveTimes = solve(queue);
                result = Math.max(result, Math.max(solveTimes - rest0, exclusive) + rest0);
                sum1 = sum11 = sum0 = 0;
                sum00 = rest;
            } else if ((mac[i] > avg || n - 1 == i && avg == mac[i]) && sum00 > 0){
                System.out.println("3. sum00 = " + sum00);
                queue.add(sum00);
                sum00 = 0;
            } else if ((mac[i] < avg || n - 1 == i && avg == mac[i]) && sum11 > 0) {
                System.out.println("4. sum11 = " + sum11);
                queue.add(sum11);
                sum11 = 0;
            }
        }
        return result;
    }


    public int solve(ArrayList<Integer> queue) {

        int result = 0;
        while (queue.size() > 0) {
            int min = Collections.min(queue);
            int size = queue.size();
            for (int i = size - 1; i >= 0; i--) {
                int v = queue.get(i);
                if (v == min) {
                    queue.remove(i);
                    if (i >= 1 && i < size - 1) {
                        queue.set(i - 1, queue.get(i) + queue.get(i - 1));
                    }
                } else {
                    queue.set(i, v - min);
                }
            }
            result += min;
        }
        return result;
    }

    public static void main1(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(0, 2, 4, 8, 6, 10, 12));

        Test517_3 t = new Test517_3();
        System.out.println(t.solve(list));
//        System.out.println(t.findMinMoves(list));
    }

    public static void main(String[] args) {
        Test517_3 t = new Test517_3();
        int[] nums = {0, 3, 0};
        System.out.println(t.findMinMoves(nums));
    }
}

































