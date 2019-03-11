package test;

/*
*
* In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.

未完成， 而且没有考虑重复数字
* */
public class Test464 {
    private int maxChoosableInteger;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
//        boolean[] dpMin = new boolean[desiredTotal + maxChoosableInteger + 1];
//        boolean[] dpMax = new boolean[desiredTotal + maxChoosableInteger + 1];
//        boolean player1 = true;
//        boolean player2 = false;
//
//        boolean p1 = player1;
//        boolean p2 = player2;
//
//        for (int i = 1; i <= desiredTotal; ){
//            dpMin[i] = p1;
//            for (int j = 1; j <= maxChoosableInteger; j ++){
//                dpMin[i + j] = p2;
//                dpMax[i + j + maxChoosableInteger - 1] = p1;
//            }
//            dpMax[i + maxChoosableInteger - 1] = p1;
//            i += maxChoosableInteger;
//            boolean temp = p1;
//            p1 = p2;
//            p2 = temp;
//        }


        this.maxChoosableInteger = maxChoosableInteger;
        boolean p1 = true;
        boolean p2 = false;
        return pMax(desiredTotal - 1, p2);
    }


    public boolean pMax(int index, boolean p){
        return (index == 0 && p) && pMin(index - maxChoosableInteger, !p);
    }

    public boolean pMin(int index, boolean p){
        if (index < 0){ return false; }
        return pMax(index - 1, !p);
    }

}
