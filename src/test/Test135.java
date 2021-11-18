package test;

public class Test135 {

    public int candy(int[] ratings) {
        if (ratings.length <= 1) return 1;
        int[] need0 = new int[ratings.length];
        int[] need1 = new int[ratings.length];
        need0[0] = 1;
        need1[ratings.length - 1] = 1;
        for (int i = 1, j = ratings.length - 2; i < ratings.length; i ++, j--) {
            need0[i] = 1; need1[j] = 1;
            if (ratings[i] > ratings[i - 1]) need0[i] = need0[i - 1] + 1;
            if (ratings[j] > ratings[j + 1]) need1[j] = need1[j + 1] + 1;
        }

        int sum = 0;
        for (int i = 0; i < ratings.length; i ++) {
            sum += Math.max(need0[i], need1[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        Test135 t = new Test135();
//        int[] kids = {4, 3, 1, 8, 2, 2, 2, 1, 3, 9, 8, 8, 8, 7, 6, 5, 4};
        //            3  2  1  2  1  1  2  1  2  3, 1, 1, 5, 4, 3, 2, 1
//        int[] kids = {29, 51, 87, 87, 72, 12};
        //            1,  2,  3,  3,  2,  1
//        int[] kids = {1, 2, 3, 5, 4, 3, 2, 1, 4, 3, 2, 1, 3, 2, 1, 1, 2, 3, 4};
        //            1, 2, 3, 5, 4, 3, 2, 1, 4, 3, 2, 1, 3, 2, 1, 1, 2, 3, 4

        int[] kids = {1, 2};
        int result = t.candy(kids);
        System.out.println(result);
    }
}
