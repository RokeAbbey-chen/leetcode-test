package test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Test127_2 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        char[][] words = new char[wordList.size() + 1][];
        int i = 0;
        int endIndex = -1;
        for (String word: wordList) {
            if (endWord.equals(word)) endIndex = i;
            words[i] = word.toCharArray();
            i ++;
        }
        if (-1 == endIndex) return 0;

        words[words.length - 1] = beginWord.toCharArray();
        return search(endIndex, words);

    }

    public int search(int endIndex, char[][] words) {
        LinkedList<Integer> smallQueue = new LinkedList<>();
        LinkedList<Integer> bigQueue = new LinkedList<>();

        //                          index           level
        smallQueue.addLast(words.length - 1);
        bigQueue.addLast(endIndex);
        HashSet<Integer> smallVisited = new HashSet<>();
        HashSet<Integer> bigVisited = new HashSet<>();
        smallVisited.add(words.length - 1);
        bigVisited.add(endIndex);

        int level = 2;
        while (smallQueue.size() > 0 && bigQueue.size() > 0) {
            if (smallQueue.size() > bigQueue.size()) {
                LinkedList<Integer> tmp = smallQueue;
                smallQueue = bigQueue;  bigQueue = tmp;

                HashSet<Integer> tmp2 = smallVisited;
                smallVisited = bigVisited;
                bigVisited = tmp2;
            }

            LinkedList<Integer> shareQueue = new LinkedList<>();
            while (!smallQueue.isEmpty()) {
                int index = smallQueue.removeFirst();
                for (Integer suc : getSuccessors(index, words)) {
                    if (bigVisited.contains(suc)) return level;
                    if (smallVisited.contains(suc)) continue;
                    shareQueue.addLast(suc);
                    smallVisited.add(suc);
                }
            }
            smallQueue = shareQueue;
            level ++;
        }
        return 0;
    }

    public LinkedList<Integer> getSuccessors(int index, char[][] words) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < words.length; i ++) {
            if (i == index) continue;
            if (1 == differentCount(words[index], words[i])) list.addLast(i);
        }
        return list;
    }

    public int differentCount(char[] word0, char[] word1) {
        int count = 0;
        for (int i = 0; i < word0.length; i ++) if (word0[i] != word1[i]) count ++;
        return count;
    }

}
