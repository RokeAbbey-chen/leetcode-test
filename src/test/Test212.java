package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 超时
 */
public class Test212 {

    public List<String> findWords(char[][] board, String[] words) {

        List<int[]>[] where = new ArrayList[26];
        for (int i = 0; i < 26; i ++) where[i] = new ArrayList<>();

        for (int i = 0; i < board.length; i ++)
            for (int j = 0; j < board[i].length; j ++)
                where[board[i][j] - 'a'].add(new int[]{i, j});

        char[][] chs = new char[words.length][];

        for (int i = 0; i < chs.length; i ++)
            chs[i] = words[i].toCharArray();

        List<String> result = new ArrayList<>();
        boolean[][] mask = new boolean[board.length][board[0].length];
        for (int i = 0; i < chs.length; i ++) {
            if (words[i].equals("oathii")) {
                System.out.println("line 28");
            }
            if (where[chs[i][0] - 'a'].size() > 0 &&
                    search(where[chs[i][0] - 'a'], where, chs[i], 0, mask)) {
                result.add(words[i]);
            }
        }
        return result;
    }

    public boolean search(List<int[]> currentAt, List<int[]>[] where, char[] word, int curIndex, boolean[][] mask) {
        if (word.length - 1 == curIndex) return true;
        int size = currentAt.size();
        for (int i = 0; i < size; i ++) {
            int[] at = currentAt.get(i);
            mask[at[0]][at[1]] = true;
            List<int[]> neighbors = findNeighbors(at, where, word, curIndex, mask);
            if (null == neighbors || neighbors.size() <= 0) {
                mask[at[0]][at[1]] = false;
                continue;
            }
            if(search(neighbors, where, word, curIndex + 1, mask)) {
                mask[at[0]][at[1]] = false;
                return true;
            }
            mask[at[0]][at[1]] = false;
        }
        return false;
    }

    public List<int[]> findNeighbors(int[] at, List<int[]>[] where, char[] word, int curIndex, boolean[][] mask) {
        if (curIndex + 1 >= word.length) return null;
        int size = where[word[curIndex + 1] - 'a'].size();
        List<int[]> neighbors = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            int[] nei = where[word[curIndex + 1] - 'a'].get(i);
            if (Math.abs(nei[0] - at[0]) + Math.abs(nei[1] - at[1]) == 1 && !mask[nei[0]][nei[1]]) {
                neighbors.add(nei);
            }
        }
        return neighbors;
    }

    public static void main(String[] args) {
        Test212 t = new Test212();
//        char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] words = {"oath","pea","eat","rain","oaaan", "aak", "aan"};

//        char[][] board = new char[][]{{'a'}};
//        String[] words = {"b"};

//        char[][] board = new char[][]{{'a', 'a'}, {'a', 'a'}};
//        String[] words = {"aaa", "aaaa", "aaaaa"};

        char[][] board = {{'o','a','a','n'},
                          {'e','t','a','e'},
                          {'i','h','k','r'},
                          {'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain","oathi","oathk","oathf","oate","oathii","oathfi","oathfii"};
        Arrays.sort(words);
//        for (String str: words) {
//            System.out.println(str);
//        }

        List<String> result = t.findWords(board, words);
        System.out.println("result: " + result);
    }
}
