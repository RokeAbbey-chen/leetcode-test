package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*超时*/
public class Test336 {
    public List<List<Integer>> palindromePairs(String[] words) {
        char[][] chs = new char[words.length][];
        int[] identities = new int[words.length];
        for (int i = 0; i < chs.length; i ++) {
            chs[i] = words[i].toCharArray();
            identities[i] = getIdentity(chs[i]);
        }

        LinkedList<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < words.length; i ++) {
            for (int j = i + 1; j < words.length; j ++) {
                if ((identities[i] ^ identities[j]) != identities[i] - identities[j]
                    && (identities[i] ^ identities[j]) != identities[j] - identities[i]) continue;
                if (isPalindrome(i, j, chs)) {
                    result.add(Arrays.asList(i, j));
                }
                if (isPalindrome(j, i, chs)) {
                    result.add(Arrays.asList(j, i));
                }
            }
        }
        return result;
    }

    public static boolean isPalindrome(int first, int second, char[][] words) {
        char[] firstWord = words[first];
        char[] secondWord = words[second];
        for (int i = 0, j = words[second].length - 1;
             i - firstWord.length <= j
                     && i - firstWord.length < secondWord.length
                     && j + firstWord.length >= 0;
             i ++, j --) {
            char c0 = i < firstWord.length ? firstWord[i]: secondWord[i - firstWord.length];
            char c1 = j >= 0 ? secondWord[j]: firstWord[j + firstWord.length];
            if (c0 != c1) {
                return false;
            }
        }
        return true;
    }

    public int getIdentity(char[] word) {
        int id = 0;
        for (int i = 0; i < word.length; i ++) {
            id |= 1 << (word[i] - 'a');
        }
        return id;
    }

    public static void main(String[] args) {
        Test336 t = new Test336();
        String[] words = new String[]{
//                "aaa", ""
                "cdab", "adc", "cda", "bad"
        };
        List<List<Integer>> result = t.palindromePairs(words);
        System.out.println(result);
    }
}
