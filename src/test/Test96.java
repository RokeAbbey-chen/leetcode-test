package test;
/*
*
* Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*
* */
public class Test96 {
    public static void main(String[] args) {
        Test96 t = new Test96();
        int n = 3;
        System.out.println(t.numTrees(n));
    }
    public int numTrees(int n) {
        int[] result = new int[Math.max(n + 1, 3)];
        result[0] = 1;
        result[1] = 1;
        result[2] = 2;
        for (int i = 3; i <= n; i++) {
            for(int j = 0; j <= i - 1; j ++){ result[i] += result[j] * result[i - j - 1]; }
        }
        return result[n];
    }
}
