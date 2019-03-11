package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 514. Freedom Trail
 Hard

 In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.

 Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.

 Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.

 At the stage of rotating the ring to spell the key character key[i]:

 You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
 If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.

 Example:


 Input: ring = "godding", key = "gd"
 Output: 4
 Explanation:
 For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 Also, we need 1 more step for spelling.
 So the final output is 4.

 Note:

 Length of both ring and key will be in range 1 to 100.
 There are only lowercase letters in both strings and might be some duplcate characters in both strings.
 It's guaranteed that string key could always be spelled by rotating the string ring.

 */
public class Test514 {
    /**
     * godding gd
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps(String ring, String key) {
        int result = Integer.MAX_VALUE;
        char[] keyc = key.toCharArray();
        char[] ringc = ring.toCharArray();
        int[][][] dp = new int[ringc.length][ringc.length][2];
        HashMap<Character, ArrayList<Integer>> indexes = new HashMap<>();
        for (int i = 0; i < ringc.length; i ++){
            char c = ringc[i];
            ArrayList<Integer> ind = indexes.get(c);
            if (ind == null){
                ind = new ArrayList<>();
                indexes.put(c, ind);
            }
            ind.add(i);
        }
        ArrayList<Integer> head = indexes.get(keyc[0]);
        for (int h : head){
            dp[h][h][1] = Math.min(h, ringc.length - h);
        }

        for (int j = 1; j < keyc.length; j ++){
            ArrayList<Integer> tail = indexes.get(keyc[j]);
            ArrayList<Integer> pTail = indexes.get(keyc[j - 1]);
            for (int h : head){
                for (int t : tail){
                    dp[h][t][(j + 1) & 1] = Integer.MAX_VALUE;
                    if (j != 1) {
                        for (int pt : pTail) {
                            int distance = Math.min(Math.abs(t - pt), Math.min(t, pt) + ringc.length - Math.max(t, pt));
                            dp[h][t][(j + 1) & 1] = Math.min(dp[h][pt][j & 1] + distance, dp[h][t][(j + 1) & 1]);

                        }
                    } else {
                        int distance =  Math.min(Math.abs(t - h), Math.min(t, h) + ringc.length - Math.max(t, h));
                        dp[h][t][(j + 1) & 1] = Math.min(dp[h][h][j & 1] + distance, dp[h][t][(j + 1) & 1]);
                    }
                }
            }
            if (j == keyc.length - 1){
                for (int h : head) {
                    for (int t : tail) {
                        result = Math.min(dp[h][t][(j + 1) & 1], result);
                    }
                }
            }
        }
        return result + keyc.length;
    }
    /**
     *
     "ababcab"
     "acbaacba"

     */
    public static void main(String[] args) {
        Test514 t = new Test514();
        String ring = "ababcab";
        String key = "acbaacba";
        System.out.println(t.findRotateSteps(ring, key));
    }
}
