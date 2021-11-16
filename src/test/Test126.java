package test;

import java.util.*;

public class Test126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        char[][] words = new char[wordList.size() + 1][];
        int i = 0;
        int endIndex = -1;
        for (String word: wordList) {
            if (endWord.equals(word)) endIndex = i;
            words[i] = word.toCharArray();
            i ++;
        }
        if (-1 == endIndex) new ArrayList<>(0);
        words[words.length - 1] = beginWord.toCharArray();
        return search(endIndex, words, wordList);
    }

    public List<List<String>> search(int endIndex, char[][] words, List<String> wordsStr) {

        HashSet<Integer>[] bestPredecessor = new HashSet [words.length];
        int[][] bestLevels = new int[2][words.length];

        int inf = Integer.MAX_VALUE - 10;
        for (int i = 0; i < words.length; i ++) {
            bestPredecessor[i] = new HashSet<>();
            bestLevels[0][i] = inf;
            bestLevels[1][i] = inf;
        }
        bestLevels[0][words.length - 1] = 1;
        bestLevels[1][endIndex] = 1;
        int [] levels = {1, 1};

        LinkedList<Integer>[] queues = new LinkedList[]{new LinkedList(), new LinkedList()};
        queues[0].add(words.length - 1);
        queues[1].add(endIndex);
        ArrayList<List<String>> result = new ArrayList<>();

        while (!queues[0].isEmpty() && !queues[1].isEmpty()) {
            int which = 0;
            if (queues[0].size() > queues[1].size()) which = 1;

            LinkedList<Integer> sharedQueue = new LinkedList<>();
            boolean shouldFinish = false;
            while (!queues[which].isEmpty()) {
                int index = queues[which].removeFirst();
                LinkedList<Integer> successors = getSuccessors(index, words);
                for (Integer suc: successors) {
                    if (bestLevels[(which + 1) & 1][suc] < inf) {
                        /** 在对面找到后继，直接加入答案 */
                        shouldFinish = true;
                        List<LinkedList<Integer>> paths = concat(index, suc, bestPredecessor, which);
                        result.addAll(transInt2Str(paths, words));
                    } else if (bestLevels[which][suc] < inf) {
                        /** 发现环，continue*/
                        continue;
                    } else if (!shouldFinish){
                        /** 如果连通 就不需要进行这些工作，准备返回就行了*/
                        bestLevels[which][suc] = levels[which];
                        bestPredecessor[suc].add(index);
                        sharedQueue.addLast(suc);
                    }
                }
            }
            if (shouldFinish) return result;

            levels[which] ++;
            queues[which] = sharedQueue;

        }

        return result;
    }


    public List<LinkedList<Integer>> concat(int index0, int index1, HashSet<Integer>[] predecessor, int reverse) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        LinkedList<LinkedList<Integer>> list = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        if (0 == reverse) {path.add(index0); path.add(index1);}
        if (1 == reverse) {path.add(index1); path.add(index0);}
        list.add(path);


        for (LinkedList<Integer> intPath: list) {
            LinkedList<LinkedList<Integer>> firstPart = unfold(predecessor[index0], list, reverse);
            LinkedList<LinkedList<Integer>> whole = unfold(predecessor[index1], firstPart, 1 - reverse);
            result.addAll(whole);
        }

        return result;
    }

    public LinkedList<LinkedList<Integer>> unfold(HashSet<Integer> set, List<LinkedList<Integer>> list, int reverse) {
        LinkedList<LinkedList<Integer>> result = new LinkedList<>();
        for (Integer i : set) {
            for (LinkedList<Integer> pathStrList: list) {
                LinkedList<Integer> tmp = (LinkedList<Integer>) pathStrList.clone();
                if (reverse == 0) tmp.addFirst(i);
                else tmp.addLast(i);
                result.add(tmp);
            }
        }
        return result;
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

    public List<List<String>> transInt2Str(List<LinkedList<Integer>> intPaths, char[][] words) {
        ArrayList<List<String>> result = new ArrayList<>();
        for (LinkedList<Integer> path: intPaths) {
            ArrayList<String> sPath = new ArrayList<>();
            for (int i : path) {
                sPath.add(new String(words[i]));
            }
            result.add(sPath);
        }
        return result;
    }

    public static void main(String[] args) {
        Test126 t = new Test126();
        String begin = "hel";
        String end = "obd";
        List<String> words = Arrays.asList("hed", "hbl", "hbd", "obd");
        System.out.println(t.findLadders(begin, end, words));
    }
}
