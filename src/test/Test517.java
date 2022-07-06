package test;

public class Test517 {
    public int findMinMoves(int[] machines) {
        int sum = 0;
        for (int i = 0; i < machines.length; i ++) sum += machines[i];
        int avg = sum / machines.length;
        if (avg * machines.length != sum) return -1;

        int i = 0;
        for (; i < machines.length && machines[i] == avg; i ++);

        if (machines.length == i) return 0;

//        if (machines[i] > avg) {
//            for (i = 0; i < machines.length; i ++) machines[i] = -machines[i];
//            avg = -avg;
//        }

        int i0 = 0, i1 = 1;
        sum = machines[0];
        int left = 0, right = 0, n = machines.length;

        for (; i1 < n; ) {
            int adder = machines[i1];
            if (sum < i1 * avg && machines[i1] > avg) {
                int moveNum = Math.min(Math.abs(avg - machines[i0]), machines[i1] - avg);
                left = Math.max(i1 - i0 + moveNum, left);
                machines[i0] += moveNum;
                for (;machines[i0] == avg && i0 < i1; i0 ++);
                if (moveNum == machines[i1] - avg) {
                    i1 ++;
                    sum += adder;
                } else sum += moveNum;
                machines[i1] -= moveNum;

            } else if (sum > i1 * avg && machines[i1] < avg) {
                int moveNum = Math.min(Math.abs(avg - machines[i0]), avg - machines[i1]);
                right = Math.max(i1 - i0 + moveNum, right);
                machines[i0] -= moveNum;
                for (;machines[i0] == avg && i0 < i1; i0 ++);
                if (moveNum == avg - machines[i1]) {
                    i1 ++;
                    sum += adder;
                } else sum -= moveNum;
                machines[i1] += moveNum;
            }
        }
        return left + right;
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
        int[] nums = {0, 3, 0};
        int result = t.findMinMoves(nums);
        System.out.println("result = " + result);

    }
}
