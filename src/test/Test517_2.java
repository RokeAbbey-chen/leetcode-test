package test;

public class Test517_2 {
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

        int moveTimes = 0;

        int needFill0 = 0, needFill = 0;
        int needMove0 = 0, needMove = 0;
        sum = 0;
        int exclude = 0;
        int rest = 0;
        int left = 0, right = 0;
//        int[] nums = {1, 0, 5, 7, 9, 10, 12, 4, 24};
        for (int i = 0; i < n; i ++) {
            needFill += Math.max(avg - mac[i], 0);
            needMove += Math.max(mac[i] - avg, 0);
            System.out.println("needFill = " + needFill + ", needMove = " + needMove + ", mac[" + i + "] = " + mac[i]);
            if (sum < i * avg && (sum + mac[i] >= (i + 1) * avg
                    || (i + 1) < n && mac[i] >= avg && mac[i + 1] < avg)) {
                if (sum + mac[i] > (i + 1) * avg) {
                    rest = sum + mac[i] - (i + 1) * avg;
                    exclude = mac[i] - avg - rest;//(i + 1) * avg - sum;
                }
                int realMove = Math.min(needFill + needFill0, needMove);
//                moveTimes = Math.max(moveTimes, realMove - Math.min(needFill, realMove));
                left = realMove + Math.max(0, left - Math.min(needFill, realMove));
                moveTimes = Math.max(moveTimes, left);
                needFill -= realMove;
                needMove -= realMove;
                needFill0 += needFill;
//                needMove0 += needMove;
                needFill = 0; // needMove = 0;
            } else if (sum > i * avg && (sum + mac[i] <= (i + 1) * avg
                    || (i + 1) < n && mac[i] <= avg && mac[i + 1] > avg)) {
                int realMove = Math.min(needFill, needMove + needMove0);
                right = realMove + Math.max(0, right - Math.min(needMove, realMove));
                moveTimes = Math.max(right, moveTimes);
                moveTimes = Math.max(moveTimes, Math.max(realMove - rest, exclude) + rest);
                needMove -= realMove;
                needFill -= realMove;
                needMove0 += needMove;
//                needFill0 += needFill;
                needMove = 0;
                if (realMove >= exclude) rest = Math.max(rest + exclude - realMove, 0);
                if (0 == rest) exclude = 0;
            }
            sum += mac[i];
        }
        return moveTimes;


    }

    public static void main(String[] args) {
        Test517_2 t = new Test517_2();
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
        int result = t.findMinMoves(nums);

        System.out.println("result = " + result);

    }
}
