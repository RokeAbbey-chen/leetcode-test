package test;

public class Test517 {
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

        int i0, i1;

        for (i1 = 1; i1 < n; ) {

        }

    }
    public static void main1(String[] args) {
        for (long i = Long.MAX_VALUE; i >= Long.MIN_VALUE; i --) {
            long a = i;
            double b = (double) a;
            long c = (long) b;
            if (c != a) {
                System.out.println("a = " + a + ", b = " + b + ", c = " + c);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Test517 t = new Test517();
        int[] nums = {0, 3, 10, 2, 0};
        int result = t.findMinMoves(nums);
        System.out.println("result = " + result);

    }
}
