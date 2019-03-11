package test;

/*
* Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
Credits:
Special thanks to @ syedee for adding this problem and creating all test cases.
*
* */
public class Test338 {
    public int[] countBits(int num) {
        if (num == 0){ return new int[]{0}; }
        if (num == 1){ return new int[]{0, 1}; }
        int t = 2;
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= num; i ++){
            if (i == t << 1){
                dp[i] = 1;
                t <<= 1;
            } else {
                dp[i] = dp[i % t] + 1;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        Test338 t = new Test338();
        int num = 5;
        System.out.println(t.countBits(num));
    }
}
