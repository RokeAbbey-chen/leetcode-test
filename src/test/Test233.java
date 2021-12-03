package test;

public class Test233 {

    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        //               个  十
        int[] nMap = {0, 1, 20, 300, 4000, 50000, (int)6e5, (int)7e6, (int)8e7, (int)9e8, (int)10e9};
        long maxNum = (long)10e9;
        int index = nMap.length;
        while (maxNum >= n + 1) {
            maxNum /= 10;
            index --;
        }

        int count = 0;
        while(maxNum > 0) {
            if (n >= maxNum) {
                if (n >= maxNum << 1) {
                    count += maxNum;  // 最高位的1全部拿到
                } else {
                    count += n % maxNum + 1;
                }
                count += n / maxNum * nMap[index - 1];
                n %= maxNum;
            }
            maxNum /= 10;
            index --;
        }
        return count;


        //

        //99999    50000
        // 9999    4000
        //  999    300
        //   99    20
        //    9    1
//        12345    4000 + 2346 + 2 * 300 + 1000 + 3 * 20 +100 + 4 * 1 + 10 + 1
//        110123   50000 + 10124 + 4000 + 124 + 0 + 20 + 24 + 2 + 4
    //        64305

    }

    public static void main(String[] args) {
        Test233 t = new Test233();
//        System.out.println(t.countDigitOne(12));
        System.out.println(t.countDigitOne(110123));
    }
}
