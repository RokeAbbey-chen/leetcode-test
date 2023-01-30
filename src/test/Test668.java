package test;

public class Test668 {
    public int findKthNumber(int m, int n, int k) {
        if (m > n) {
            int tmp = n;
            n = m;
            m = tmp;
        }
        int l = 0, r = m * n;
        int[] cm = {-1, -1};
        for (int mid = (l + r) >> 1; l <= r; mid = (l + r) >> 1) {
            cm = find(mid, m, n);
            if (cm[0] > k) {
                r = mid - 1;
            } else if (cm[0] < k) {
                l = mid + 1;
            } else {
                return cm[1];
            }

        }
        if (cm[0] > k) return cm[1];

        int result = Integer.MAX_VALUE;
        for (int mid = cm[1], i = 1; i <= m; i ++) {
            if (mid >= i * n) continue;
            result = Math.min((mid + i) / i * i, result);
        }
        return result;


    }

    public int[] find(int v, int m, int n) {
        int count = 0;
        int maxV = 0;
        for (int i = 1; i <= m; i ++) {
            int add = Math.min(v / i, n);
            count += add;
            maxV = Math.max(add * i, maxV);
        }
        return new int[]{count, maxV};
    }


    public static void main(String[] args) {
        Test668 t = new Test668();
//        int m = 105, n = 57, k = 105 * 57;
//        int m = 105, n = 57, k = 1146;
        int m = 2, n = 2, k = 2;
        int result = t.findKthNumber(m, n, k);
        System.out.println("result = " + result);
    }

    public static void main1(String[] args) {
        int a = 11, b = 21;
        a ^= (b ^= (a ^= b));
//        int c = b ^= a ^= b;
//        a ^= b;
        System.out.println("a = " + a + ", b = " + b);
//        b ^= a;
        System.out.println("a = " + a + ", b = " + b);
//        a ^= b;
        System.out.println("a = " + a + ", b = " + b);
//        System.out.println("c = " + c + ", ^ = " + (a ^= c));
    }

}
