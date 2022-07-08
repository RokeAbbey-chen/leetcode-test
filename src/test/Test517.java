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

        System.out.println("avg = " + avg);
        int left = 0, right = 0;
        int needFilled = Math.max(0, avg - mac[0]), needMove = Math.max(0, mac[0] - avg);
        int needFilled0 = 0, needMove0 = 0;
        sum = mac[0];
        int notTogether = 0;
        int lastDir = 0;
        int nums = 0;
        for (int i1 = 1; i1 < n; i1 ++) {
            needFilled += Math.max(0, avg - mac[i1]);
            needMove += Math.max(0, mac[i1] - avg);
            if (sum < avg * i1 && (sum + mac[i1] >= avg * (i1 + 1) || i1 + 1 < n && mac[i1] > avg && mac[i1 + 1] < avg)) {
//            if (sum < avg * i1 && sum + mac[i1] >= avg * (i1 + 1) ) {
                System.out.println("i1 = " + i1);
//                notTogether = Math.max(notTogether, sum + mac[i1] - avg * (i1 + 1));
                notTogether = Math.max(notTogether, avg * i1 - sum);

                System.out.println("notTogether = " + notTogether);

                int actualMove;
                System.out.println("nf = " + needFilled + ", nf0 = " + needFilled0);
                System.out.println("nm = " + needMove + ", nm0 = " + needMove0);
                actualMove = Math.min(needFilled + needFilled0, needMove);
//                left = Math.max(left, actualMove);
//                left += actualMove;
//                left = Math.max(left, actualMove + Math.max(0, left - Math.min(needFilled, needMove)));
                left = Math.max(left, actualMove + Math.max(0, left - needFilled));
                nums = Math.max(left, nums);
                System.out.println("move left = " + left + ", actualMove = " + actualMove);
                needFilled0 += needFilled - actualMove;
//                needMove0 += needMove - actualMove;
                needMove -= actualMove;
                needFilled = 0;
            } else if (sum > avg * i1 && (sum + mac[i1] <= avg * (i1 + 1) || i1 + 1 < n && mac[i1] < avg && mac[i1 + 1] > avg)) {
//            } else if (sum > avg * i1 && sum + mac[i1] <= avg * (i1 + 1)) {
                System.out.println("i1 = " + i1);
//                if (sum + mac[i1] < avg * (i1 + 1)) notTogether++;
                System.out.println("nf = " + needFilled + ", nf0 = " + needFilled0);
                System.out.println("nm = " + needMove + ", nm0 = " + needMove0);
                int actualMove = Math.min(needMove + needMove0, needFilled);
                right = Math.max(right, actualMove + Math.max(0, right - needMove));
                nums = Math.max(right + notTogether, nums);
                notTogether = 0;
                System.out.println("move right = " + right+ ", actualMove = " + actualMove);
//                needFilled0 += needFilled - actualMove;
//                notTogether = 0;
                needMove0 += needMove - actualMove;
                needFilled -= actualMove;
                needMove = 0;
            }

            sum += mac[i1];
        }
//        return Math.max(left, right + notTogether);
//        return Math.max(Math.max(left, right), Math.min(left, right) + notTogether);
        return nums;
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
//        int[] nums = {0, 3, 0};
//        int[] nums = {4, 0, 0, 4};
//        int[] nums = {0, 4, 12, 0};
//        int[] nums = {0, 3, 10, 2, 7, 9, 4};
        int[] nums = {1, 0, 5, 7, 9, 10, 12, 4};
//        int[] nums = {1, 0, 5, 7, 9, 10, 12, 4, 24};
//        int[] nums = {0,0,10,0,0,0,10,0,0,0};
        int result = t.findMinMoves(nums);

        System.out.println("result = " + result);

    }
}
