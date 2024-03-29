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

        ArrayList<Integer> queue = new ArrayList<>();
        int sum0 = 0, sum1 = 0;
        int sum00 = 0, sum11 = 0;
        int result = 0;

        int exclusive = 0;
        int rest0 = 0;
        for (int i = 0; i < n; i ++) {

            sum00 += Math.max(0, avg - mac[i]);
            sum11 += Math.max(0, mac[i] - avg);

            if (sum0 > sum1 && sum0 + sum00 <= sum1 + sum11) {
                int rest = sum1 + sum11 - sum0 - sum00;
                exclusive = mac[i] - rest - avg;
                queue.add(sum11 - rest);
                int solveTimes = solve4(queue);
                result = Math.max(result, solveTimes);
                queue.clear();
                sum0 = sum00 = sum1 = 0;
                sum11 = rest;
                rest0 = rest;
            } else if (sum1 > sum0 && sum1 + sum11 <= sum0 + sum00) {
                int rest = sum0 + sum00 - sum1 - sum11;
                queue.add(sum00 - rest);
                int solveTimes = solve4(queue);
                queue.clear();
                result = Math.max(result, Math.max(solveTimes - rest0, exclusive) + rest0);
                sum1 = sum11 = sum0 = 0;
                sum00 = rest;
            }
            if ((i + 1 < n && mac[i + 1] > avg || n - 1 == i) && sum00 > 0){
                queue.add(sum00);
                sum0 += sum00;
                sum00 = 0;
            } else if ((i + 1 < n && mac[i + 1] < avg || n - 1 == i) && sum11 > 0) {
                queue.add(sum11);
                sum1 += sum11;
                sum11 = 0;
            }
        }
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
                    if (i >= 1 && i < size - 1) {
                        queue.set(i - 1, queue.get(i) + queue.get(i - 1));
                        queue.remove(i);
                    }
                    size --;
                } else {
                    queue.set(i, v - min);
                }
            }
            result += min;
        }
        return result;
    }


    public int solve2(Node last) {

        Node cur = last;
        int result = 0;
        while (null != cur.prev) {
            Node prev = cur.prev;
            if (cur.v <= prev.v) {
                prev.v -= cur.v;
                if (null != cur.next) {
                    prev.v += cur.next.v;
                    cur.next.next.prev = prev;
                    cur.prev.next = cur.next.next;
                }
                return  cur.v + result; // cur.v + result; //Math.max(cur.v, result);
            } else {
                result += cur.prev.v; // Math.max(cur.prev.v, result);
                cur.v -= cur.prev.v;
                cur.prev.v = 0;
                int v2 = solve2(cur.prev.prev);
                result = Math.max(result, v2);
            }

        }
        return result;
    }

    public int solve3(List<Integer> queue) {
        Node node = null;
        for (Integer i : queue) {
            if (null == node) {
                node = new Node(i);
            } else {
                Node bro = new Node(i);
                bro.prev = node;
                node.next = bro;
                node = bro;
            }
        }
        return solve2(node);
    }

    static class Node {
        Node prev;
        Node next;
        int v;
        public Node(int v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return String.valueOf(v);
        }
    }

    public int solve4(ArrayList<Integer> queue) {
        int size = queue.size();
        int result = 0;
        for (int i = 1; i < size; i += 2) {
            int v1 = queue.get(i);
            int v0 = queue.get(i - 1);
            result = Math.max(v0, result) + v1 - v0;
        }
        return result;
    }

    public static void main2(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
//        list.addAll(Arrays.asList(97, 9, 22, 42, 12, 63, 24, 41));
        list.addAll(Arrays.asList(50, 35, 40, 55));


        Test517_3 t = new Test517_3();
        System.out.println(t.solve(list));
//        System.out.println(t.findMinMoves(list));
    }

    public static void main3(String[] args) {
//        List<Integer> list = new LinkedList<>();
//        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
//        ListIterator<Integer> it = list.listIterator(list.size());
//        while (it.hasPrevious()) {
//            System.out.println(it.previous());
//        }

//        int a = 35326977;
//        float b = (float)a;
//        System.out.println("a = " + a);
//        System.out.println("b = " + (int)b);

        Node start = null;
        Node node = start;
//        int[] nums = {10, 5, 20, 8, 6, 23};
        int[] nums = {72, 53, 140, 55, 18, 36, 13, 99};

        for (int i : nums) {
            if (null == start) {
                start = new Node(i);
                node = start;
                continue;
            }
            Node bro = new Node(i);
            bro.prev = node;
            node.next = bro;
            node = bro;
        }

        Test517_3 t = new Test517_3();
        System.out.println(t.solve2(node));
        System.out.println("----");
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
//        int[] nums = {50,62,75,31,2,84,20,74,49,73};
//        int[] nums = {91,96,36,67,3,57,13,14,69,4};
        int[] nums = {26,74,29,50,72,57,8,83,9,22};
//        int[] nums = {61,43,27,2,43,48,27,75,3,73,11,97,79,78,51,
//                81,4,62,83,19,41,26,16,63,35,26,54,21,52,61,1,90,97,80,
//                75,2,62,90,84,30,34,19,9,86,87,19,83,2,67,67,11,60,66,
//                35,35,17,36,31,36,54,31,72,63,57,54,21,83,73,10,8,50,
//                91,26,55,68,79,26,44,52,43,52,42,21,70,58,85,44,81,36,
//                94,9,28,51,72,85,33,96,63,88,59};
        System.out.println(t.findMinMoves(nums));
    }
}

































