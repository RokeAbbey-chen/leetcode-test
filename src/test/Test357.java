package test;

/*
* Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])

Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.
*
* */
public class Test357 {
    private int[] result = new int[11];
    {
        result[0] = 1;
        result[1] = 10;
        int product = 9;
        for (int i = 2; i <= 10; i++) {
            product *= (10 - i + 1);
            result[i] = result[i - 1] + product;
        }
    }
    public int countNumbersWithUniqueDigits(int n) {
        return result[Math.min(n, 10)];
    }
}
