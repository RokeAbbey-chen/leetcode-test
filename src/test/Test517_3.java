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

            System.out.println("i = " + i + ", mac[i] = " + mac[i]);
            sum00 += Math.max(0, avg - mac[i]);
            sum11 += Math.max(0, mac[i] - avg);

            System.out.println("sum0=" + sum0 +", sum00=" + sum00 + ", sum1=" + sum1 + ", sum11=" + sum11);
            if (sum0 > sum1 && sum0 + sum00 <= sum1 + sum11) {
                System.out.println("W1. sum0 = " + sum0 + ", sum1 = " + sum1);
                int rest = sum1 + sum11 - sum0 - sum00;
                exclusive = mac[i] - rest - avg;
                queue.add(exclusive);
                int solveTimes = solve(queue);
                System.out.println("result = " + result + ", solveTimes = " + solveTimes);
                result = Math.max(result, solveTimes);
                System.out.println("result = " + result);
                queue.clear();
                sum0 = sum00 = sum1 = 0;
                sum11 = rest;
                rest0 = rest;
            } else if (sum1 > sum0 && sum1 + sum11 <= sum0 + sum00) {
                System.out.println("W2. sum0 = " + sum0 + ", sum1 = " + sum1);
                int rest = sum0 + sum00 - sum1 - sum11;
                queue.add(mac[i] - rest - avg);
                int solveTimes = solve(queue);
                System.out.println("result = " + result + ", solveTimes = " + solveTimes);
                queue.clear();
                result = Math.max(result, Math.max(solveTimes - rest0, exclusive) + rest0);
                System.out.println("result = " + result);
                sum1 = sum11 = sum0 = 0;
                sum00 = rest;
            } else if ((i + 1 < n && mac[i + 1] > avg || n - 1 == i) && sum00 > 0){
                System.out.println("W3. sum00 = " + sum00);
                queue.add(sum00);
                sum0 += sum00;
                sum00 = 0;
            } else if ((i + 1 < n && mac[i + 1] < avg || n - 1 == i) && sum11 > 0) {
                System.out.println("W4. sum11 = " + sum11);
                queue.add(sum11);
                sum1 += sum11;
                sum11 = 0;
            }
            System.out.println("--------------------");
        }
//        int[] nums = {50,62,75,31,2,84,20,74,49,73};
        if (queue.size() > 0) {
            result = Math.max(solve(queue), result);
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
                    size --;
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
//        int[] nums = {0, 3, 0};
        //        int[] nums = {0, 3, 0};
//        int[] nums = {4, 0, 0, 4};
//        int[] nums = {0, 4, 12, 0};
//        int[] nums = {0, 3, 10, 2, 7, 9, 4};
//        int[] nums = {1, 0, 5, 7, 9, 10, 12, 4};
//        int[] nums = {1, 0, 5, 7, 9, 10, 12, 4, 24};
//        int[] nums = {0,0,10,0,0,0,10,0,0,0};
//        int[] nums = {0,0,14,0,10,0,0,0};
//        int[] nums = {91,95,31,48,28,41,32,29,81,4};
//        int[] nums = {44,46,11,2,12,64,40,60,92,9};
        int[] nums = {50,62,75,31,2,84,20,74,49,73};
        System.out.println(t.findMinMoves(nums));
    }
}

































