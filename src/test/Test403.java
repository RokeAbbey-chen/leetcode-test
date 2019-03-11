package test;

import java.util.Arrays;
import java.util.HashSet;

/*
* A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping
1 unit to the 2nd stone, then 2 units to the 3rd stone, then
2 units to the 4th stone, then 3 units to the 6th stone,
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as
the gap between the 5th and 6th stone is too large.
*
* */
public class Test403 {
    HashSet<Integer>[] sets = null;

    public boolean canCross(int[] stones) {
        int N = stones.length;
        if (N <= 1) {
            return true;
        } else if (stones[N - 1] > (N + 1) * N >> 1) {
            return false;
        }
        sets = new HashSet[stones.length];
        return canCross(stones, 0, 1);
    }

    private boolean canCross(int[] stones, int cur, int step) {
        if (cur == stones.length - 1) {
            return true;
        }
        if (sets[cur] == null) {
            sets[cur] = new HashSet<>();
        }
        if (sets[cur].contains(step)) {
            return false;
        }
        sets[cur].add(step);

        int i = Arrays.binarySearch(stones, cur, stones.length, stones[cur] + step);
        if (i >= 0) {
            return canCross(stones, i, step + 1)
                    || canCross(stones, i, step)
                    || step > 1 && canCross(stones, i, step - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        Test403 t = new Test403();
        int[] stones = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        stones = new int[]{0, 1, 2, 3, 4, 8, 9, 11};
        System.out.println(t.canCross(stones));
    }
}
