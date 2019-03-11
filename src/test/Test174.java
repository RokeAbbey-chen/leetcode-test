package test;

/*
*
*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.



Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)


Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*
*
* [
* [-2,-3,3],
* [-5,-10,1],
* [10,30,-5]]
* */
public class Test174 {
    public static void main(String[] args) {
        Test174 t = new Test174();
        int[][] dungeon = new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(t.calculateMinimumHP(dungeon));
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int len1 = dungeon.length;
        int len2 = dungeon[0].length;
        int[][] hurt = new int[2][len2];
        hurt[(len1 - 1) & 1][len2 - 1] = Math.min(dungeon[len1 - 1][len2 - 1], 0);

        for (int i = len2 - 2; i >= 0; i --){
            hurt[(len1 - 1) & 1][i] = tripleMin(hurt[(len1 - 1) & 1][i + 1] + dungeon[len1 - 1][i]
                    , dungeon[len1 - 1][i], 0);
        }

        for (int i = len1 - 2; i >= 0; i --){
            hurt[i & 1][len2 - 1] = tripleMin(hurt[(i + 1) & 1][len2 - 1] + dungeon[i][len2 - 1]
                    , dungeon[i][len2 - 1], 0);
            for (int j = len2 - 2; j >= 0; j --){
                hurt[i & 1][j] = Math.max(tripleMin(hurt[(i + 1) & 1][j] + dungeon[i][j], dungeon[i][j], 0),
                        tripleMin(hurt[i & 1][j + 1] + dungeon[i][j], dungeon[i][j], 0));
            }
        }

        return - hurt[0][0] + 1;

    }

    private static int tripleMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
