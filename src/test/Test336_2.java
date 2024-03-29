package test;

import java.util.ArrayList;
import java.util.List;

public class Test336_2 {
    public List<List<Integer>> palindromePairs(String[] words) {
        Node root = new Node();
        for (int i = 0; i < words.length; ++i) {
            insert(root, i, words[i].toCharArray());
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            search(root, i, words[i].toCharArray(), result);
        }

        return result;
    }

    static class Node {
        Node[] next = new Node[26];
        List<Integer> palindromTail = new ArrayList<>();
        int leaf = -1;
    }

    private void addToResult(List<List<Integer>> result, int i, int j) {
        List<Integer> pair = new ArrayList<>();
        pair.add(i);
        pair.add(j);
        result.add(pair);
    }

    private void search(Node root, int num, char[] chars, List<List<Integer>> result) {
        for (int i = 0; i < chars.length; ++i) {
            if (root.leaf != -1 && isPalindrome(chars, i, chars.length - 1) && root.leaf != num) {
                addToResult(result, num, root.leaf);
            }

            if (root.next[chars[i] - 'a'] == null) return;

            root = root.next[chars[i] - 'a'];
        }

        if (root.leaf != -1 && root.leaf != num) addToResult(result, num, root.leaf);

        for (int i : root.palindromTail) {
            if (i != num) addToResult(result, num, i);
        }
    }

    private void insert(Node root, int num, char[] chars) {
        for (int i = chars.length - 1; i>=0; --i) {
            if (isPalindrome(chars, 0, i)) root.palindromTail.add(num);

            Node child = root.next[chars[i] - 'a'];

            if (child == null) {
                child = new Node();
                root.next[chars[i] - 'a'] = child;
            }

            root = child;
        }
        root.leaf = num;
    }

    private boolean isPalindrome(char[] chars, int i, int j) {
        while (i < j) {
            if (chars[i] != chars[j]) return false;
            ++i;--j;
        }

        return true;
    }
}
