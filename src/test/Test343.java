package test;

/*
*
* Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.
* */
public class Test343 {
    public static void main(String[] args) {
        Test343 t = new Test343();
        int n = 10;
        n = 8;
        System.out.println(t.integerBreak(n));
    }
    public int integerBreak(int n) {
        int[] product = new int[n + 1];
        product[1] = 1;
        return integerBreak(n, product);
    }
    public int integerBreak(int n, int[] product){
        int max = 0;
        if (product[n] != 0){ return product[n]; }
        for (int i = 1; i < n; i ++){
            int it1 = integerBreak(i, product);
            int it2 = integerBreak(n - i, product);
            max = Math.max(max, it1 * it2);
            max = Math.max(max, i * it2);
            max = Math.max(max, it1 * (n - i));
            max = Math.max(max, i * (n - i));
        }
        product[n] = max;
        return max;
    }
}
