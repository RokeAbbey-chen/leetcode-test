package test;

public class Test440 {
    public int findKthNumber(int n, int k) {

        int n2 = n;
        int n3 = 1;
        int numDigits = 0;
        int T = 0;
        while (n2 > 0) {
            numDigits ++;
            n2 /= 10;
            n3 *= 10;
            T = 10 * T + 1;
        }
        n3 /= 10;

        int rank = 0;
        int jinweiTime = 0;
        int result = 0;

        while (T > 0) {
            if (jinweiTime < numDigits && result * 10 <= n) {
                result = 10 * result;
                rank ++;
                jinweiTime ++;
            }

            int i = jinweiTime == 1 ? 1 : 0;
            for (; rank <= k && i < 10; i ++){
                if (i < 9 &&
                    result * n3 + i * n3 >= n / n3 * n3 &&
                    rank + T - (1 * n3 - 1 - n % n3) <= k) {
                        rank += T - (1 * n3 - 1 - n % n3);//T - (n3 - 1 - n % n3);
                        T /= 10;
                        n3 /= 10;
                } else rank += T;
            }
            rank -= T;
            i --;
            result += i;
            if (rank == k) {
                return result;
            }
            T /= 10;
            n3 /= 10;
        }
        return result;
    }


    public static void main(String[] args) {
        Test440 t = new Test440();
        System.out.println(t.findKthNumber(739, 739));
    }
}
