package test;

import java.util.*;

public class Test212_2 {

    public List<String> findWords(char[][] board, String[] words) {
        Arrays.sort(words);

        String[] reverseWords = new String[words.length];
        for (int i = 0; i < words.length; i ++) {
            reverseWords[i] = new StringBuffer(words[i]).reverse().toString();
        }

        HashMap<String, List<Node>> prefixMap = getAllPrefix(words);
        ArrayList<String> prefix = new ArrayList<>();
        prefix.addAll(prefixMap.keySet());
        Collections.sort(prefix);

        HashMap<String, List<Node>> suffixMap = getAllPrefix(reverseWords);
        ArrayList<String> suffix = new ArrayList<>();
        suffix.addAll(suffixMap.keySet());
        Collections.sort(suffix);

        List<int[]>[] where = transBoard2Where(board);
        List<String> result = new ArrayList<>();
        boolean[][] mask = new boolean[board.length][board[0].length];
        for (int i = 0; i < words.length; i ++) {
//            if ("ad".equals(words[i]))
//                System.out.println("ad");
            if (!checkCharacterInBoard(words[i], where)) continue;
            boolean reverse = needReverse(words[i]);
            if (!reverse && search(words[i], prefix, prefixMap, where, mask)
                || reverse && search(reverseWords[i], suffix, suffixMap, where, mask)) {
                result.add(words[i]);
            }
        }
        return result;
    }

    public boolean needReverse(String word) {
        if (null == word || word.length() <= 2) return false;
        boolean[] set = new boolean[26];
        boolean[] set2 = new boolean[26];
        int c = 0, c2 = 0;
        for (int i = 0, j = word.length() - 1; i <= j; i ++ , j --) {
            if (!set[word.charAt(i) - 'a']) c ++;
            if (!set2[word.charAt(j) - 'a']) c2 ++;
            if (c > c2) return false;
            if (c < c2) return true;
        }
        return false;
    }
    public boolean checkCharacterInBoard(String word, List<int[]>[] where) {
        for (char c : word.toCharArray())
            if (null == where[c - 'a'] || where[c - 'a'].size() <= 0) {
                return false;
            }
        return true;
    }
    public boolean search(String word, ArrayList<String> prefix, HashMap<String, List<Node>> prefixMap,
                          List<int[]>[] where, boolean[][] mask) {
        int index = Collections.binarySearch(prefix, word);
        if (index >= 0 && prefixMap.get(prefix.get(index)).size() > 0) return true;
        index = -(index + 1) - 1; // 前缀的下标, 为负数代表没有前缀。
        if (index >= 0) {
            String pre = prefix.get(index);
            List<Node> nodes = prefixMap.get(pre);
            if (word.startsWith(pre) && searchByNode(word, nodes, where, pre.length())) {
                // 在现有路径中搜索，搜到了直接返回。
                return true;
            }
        }
        //开辟新路径
        return searchAndAdd(word, prefixMap, where, 0, mask, null);
    }

    public boolean searchAndAdd(String word, HashMap<String, List<Node>> prefixMap, List<int[]>[] where,
                                int index, boolean[][] mask, int[] pWhere) {
        if (index >= word.length())
            return true;
        char w = word.charAt(index);
        int size = where[w - 'a'].size();
//        if (word.length() == index + 1) return size > 0;

        for (int i = 0; i < size; i ++) {
            int[] curWhere = where[w - 'a'].get(i);
            /*已经遍历过，直接跳过*/
            if (mask[curWhere[0]][curWhere[1]]) continue;
            if ((pWhere == null || Math.abs(curWhere[0] - pWhere[0]) + Math.abs(curWhere[1] - pWhere[1]) <= 1)){

                mask[curWhere[0]][curWhere[1]] = true;
                String key = word.substring(0, index + 1);
                List<Node> nodeList = prefixMap != null ? prefixMap.get(key) : null;
                if (nodeList != null) {
                    // 有前缀的情况下。
                    Node node = new Node(mask);
                    node.end = curWhere;
                    nodeList.add(node);
                }

                if (searchAndAdd(word, prefixMap, where, index + 1, mask, curWhere)) {
                    mask[curWhere[0]][curWhere[1]] = false;
                    return true;
                }
                mask[curWhere[0]][curWhere[1]] = false;
            }
        }
        return false;
    }

    public List<int[]>[] transBoard2Where(char[][] board) {
        List<int[]>[] where = new ArrayList[26];
        for (int i = 0; i < where.length; i ++) {
            where[i] = new ArrayList<>();
        }
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[i].length; j ++) {
                where[board[i][j] - 'a'].add(new int[]{i, j});
            }
        }
        return where;
    }

    /**
     * 此方法不会在NodeList中新增Node
     */

    public boolean searchByNode(String word, List<Node> nodes, List<int[]>[] where, int index) {
        int size = nodes.size();
        for (int i = 0; i < size; i ++) {
            Node node = nodes.get(i);
            if(searchAndAdd(word, null, where, index, node.mask, node.end)) return true;
        }
        return false;
    }

    public HashMap<String, List<Node>> getAllPrefix(String[] words) {

        HashMap<String, List<Node>> prefix = new HashMap<>();
        for (int i = 1; i < words.length; i ++) {
            String p = getPrefix(words[i - 1], words[i]);
            if (p.isEmpty() || prefix.containsKey(p)) continue;
            prefix.put(p, new ArrayList<>(4));
        }
        return prefix;
    }
    public String getPrefix(String str0, String str1) {
        int length = Math.min(str0.length(), str1.length());
        int i = 0;
        for (; i < length && str0.charAt(i) == str1.charAt(i); i ++);
        return str0.substring(0, i);
    }

    static class Node {
        int[] end;
        boolean[][] mask;

        Node(boolean[][] mask) {
            this.mask = new boolean[mask.length][mask[0].length];
            for (int i = 0; i < mask.length; i ++) {
                for (int j = 0; j < mask[i].length; j ++) {
                    this.mask[i][j] = mask[i][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        Test212_2 t = new Test212_2();
//        char[][] board =
//                {{'o','a','a','n'},
//                {'e','t','a','e'},
//                {'i','h','k','r'},
//                {'i','f','l','v'}};
//
//        String[] words = {"oath","pea","eat","rain","oathi","oathk","oathf","oate","oathii","oathfi","oathfii"};


//        char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] words = {"oath","pea","eat","rain","oaaan", "aak", "aan"};
//        char[][] board = new char[][]{{'b','a','b','a','b','a','b','a','b','a'},
//                {'a','b','a','b','a','b','a','b','a','b'},
//                {'b','a','b','a','b','a','b','a','b','a'},
//                {'a','b','a','b','a','b','a','b','a','b'},
//                {'b','a','b','a','b','a','b','a','b','a'},
//                {'a','b','a','b','a','b','a','b','a','b'},
//                {'b','a','b','a','b','a','b','a','b','a'},
//                {'a','b','a','b','a','b','a','b','a','b'},
//                {'b','a','b','a','b','a','b','a','b','a'},
//                {'a','b','a','b','a','b','a','b','a','b'}};
//
//        String[] words = new String[] {"ababababaa","ababababab","ababababac","ababababad","ababababae","ababababaf","ababababag","ababababah","ababababai","ababababaj","ababababak","ababababal","ababababam","ababababan","ababababao","ababababap","ababababaq","ababababar","ababababas","ababababat","ababababau","ababababav","ababababaw","ababababax","ababababay","ababababaz","ababababba","ababababbb","ababababbc","ababababbd","ababababbe","ababababbf","ababababbg","ababababbh","ababababbi","ababababbj","ababababbk","ababababbl","ababababbm","ababababbn","ababababbo","ababababbp","ababababbq","ababababbr","ababababbs","ababababbt","ababababbu","ababababbv","ababababbw","ababababbx","ababababby","ababababbz","ababababca","ababababcb","ababababcc","ababababcd","ababababce","ababababcf","ababababcg","ababababch","ababababci","ababababcj","ababababck","ababababcl","ababababcm","ababababcn","ababababco","ababababcp","ababababcq","ababababcr","ababababcs","ababababct","ababababcu","ababababcv","ababababcw","ababababcx","ababababcy","ababababcz","ababababda","ababababdb","ababababdc","ababababdd","ababababde","ababababdf","ababababdg","ababababdh","ababababdi","ababababdj","ababababdk","ababababdl","ababababdm","ababababdn","ababababdo","ababababdp","ababababdq","ababababdr","ababababds","ababababdt","ababababdu","ababababdv","ababababdw","ababababdx","ababababdy","ababababdz","ababababea","ababababeb","ababababec","ababababed","ababababee","ababababef","ababababeg","ababababeh","ababababei","ababababej","ababababek","ababababel","ababababem","ababababen","ababababeo","ababababep","ababababeq","ababababer","ababababes","ababababet","ababababeu","ababababev","ababababew","ababababex","ababababey","ababababez","ababababfa","ababababfb","ababababfc","ababababfd","ababababfe","ababababff","ababababfg","ababababfh","ababababfi","ababababfj","ababababfk","ababababfl","ababababfm","ababababfn","ababababfo","ababababfp","ababababfq","ababababfr","ababababfs","ababababft","ababababfu","ababababfv","ababababfw","ababababfx","ababababfy","ababababfz","ababababga","ababababgb","ababababgc","ababababgd","ababababge","ababababgf","ababababgg","ababababgh","ababababgi","ababababgj","ababababgk","ababababgl","ababababgm","ababababgn","ababababgo","ababababgp","ababababgq","ababababgr","ababababgs","ababababgt","ababababgu","ababababgv","ababababgw","ababababgx","ababababgy","ababababgz","ababababha","ababababhb","ababababhc","ababababhd","ababababhe","ababababhf","ababababhg","ababababhh","ababababhi","ababababhj","ababababhk","ababababhl","ababababhm","ababababhn","ababababho","ababababhp","ababababhq","ababababhr","ababababhs","ababababht","ababababhu","ababababhv","ababababhw","ababababhx","ababababhy","ababababhz","ababababia","ababababib","ababababic","ababababid","ababababie","ababababif","ababababig","ababababih","ababababii","ababababij","ababababik","ababababil","ababababim","ababababin","ababababio","ababababip","ababababiq","ababababir","ababababis","ababababit","ababababiu","ababababiv","ababababiw","ababababix","ababababiy","ababababiz","ababababja","ababababjb","ababababjc","ababababjd","ababababje","ababababjf","ababababjg","ababababjh","ababababji","ababababjj","ababababjk","ababababjl","ababababjm","ababababjn","ababababjo","ababababjp","ababababjq","ababababjr","ababababjs","ababababjt","ababababju","ababababjv","ababababjw","ababababjx","ababababjy","ababababjz","ababababka","ababababkb","ababababkc","ababababkd","ababababke","ababababkf","ababababkg","ababababkh","ababababki","ababababkj","ababababkk","ababababkl","ababababkm","ababababkn","ababababko","ababababkp","ababababkq","ababababkr","ababababks","ababababkt","ababababku","ababababkv","ababababkw","ababababkx","ababababky","ababababkz","ababababla","abababablb","abababablc","ababababld","abababable","abababablf","abababablg","abababablh","ababababli","abababablj","abababablk","ababababll","abababablm","ababababln","abababablo","abababablp","abababablq","abababablr","ababababls","abababablt","abababablu","abababablv","abababablw","abababablx","abababably","abababablz","ababababma","ababababmb","ababababmc","ababababmd","ababababme","ababababmf","ababababmg","ababababmh","ababababmi","ababababmj","ababababmk","ababababml","ababababmm","ababababmn","ababababmo","ababababmp","ababababmq","ababababmr","ababababms","ababababmt","ababababmu","ababababmv","ababababmw","ababababmx","ababababmy","ababababmz","ababababna","ababababnb","ababababnc","ababababnd","ababababne","ababababnf","ababababng","ababababnh","ababababni","ababababnj","ababababnk","ababababnl","ababababnm","ababababnn","ababababno","ababababnp","ababababnq","ababababnr","ababababns","ababababnt","ababababnu","ababababnv","ababababnw","ababababnx","ababababny","ababababnz","ababababoa","ababababob","ababababoc","ababababod","ababababoe","ababababof","ababababog","ababababoh","ababababoi","ababababoj","ababababok","ababababol","ababababom","ababababon","ababababoo","ababababop","ababababoq","ababababor","ababababos","ababababot","ababababou","ababababov","ababababow","ababababox","ababababoy","ababababoz","ababababpa","ababababpb","ababababpc","ababababpd","ababababpe","ababababpf","ababababpg","ababababph","ababababpi","ababababpj","ababababpk","ababababpl","ababababpm","ababababpn","ababababpo","ababababpp","ababababpq","ababababpr","ababababps","ababababpt","ababababpu","ababababpv","ababababpw","ababababpx","ababababpy","ababababpz","ababababqa","ababababqb","ababababqc","ababababqd","ababababqe","ababababqf","ababababqg","ababababqh","ababababqi","ababababqj","ababababqk","ababababql","ababababqm","ababababqn","ababababqo","ababababqp","ababababqq","ababababqr","ababababqs","ababababqt","ababababqu","ababababqv","ababababqw","ababababqx","ababababqy","ababababqz","ababababra","ababababrb","ababababrc","ababababrd","ababababre","ababababrf","ababababrg","ababababrh","ababababri","ababababrj","ababababrk","ababababrl","ababababrm","ababababrn","ababababro","ababababrp","ababababrq","ababababrr","ababababrs","ababababrt","ababababru","ababababrv","ababababrw","ababababrx","ababababry","ababababrz","ababababsa","ababababsb","ababababsc","ababababsd","ababababse","ababababsf","ababababsg","ababababsh","ababababsi","ababababsj","ababababsk","ababababsl","ababababsm","ababababsn","ababababso","ababababsp","ababababsq","ababababsr","ababababss","ababababst","ababababsu","ababababsv","ababababsw","ababababsx","ababababsy","ababababsz","ababababta","ababababtb","ababababtc","ababababtd","ababababte","ababababtf","ababababtg","ababababth","ababababti","ababababtj","ababababtk","ababababtl","ababababtm","ababababtn","ababababto","ababababtp","ababababtq","ababababtr","ababababts","ababababtt","ababababtu","ababababtv","ababababtw","ababababtx","ababababty","ababababtz","ababababua","ababababub","ababababuc","ababababud","ababababue","ababababuf","ababababug","ababababuh","ababababui","ababababuj","ababababuk","ababababul","ababababum","ababababun","ababababuo","ababababup","ababababuq","ababababur","ababababus","ababababut","ababababuu","ababababuv","ababababuw","ababababux","ababababuy","ababababuz","ababababva","ababababvb","ababababvc","ababababvd","ababababve","ababababvf","ababababvg","ababababvh","ababababvi","ababababvj","ababababvk","ababababvl","ababababvm","ababababvn","ababababvo","ababababvp","ababababvq","ababababvr","ababababvs","ababababvt","ababababvu","ababababvv","ababababvw","ababababvx","ababababvy","ababababvz","ababababwa","ababababwb","ababababwc","ababababwd","ababababwe","ababababwf","ababababwg","ababababwh","ababababwi","ababababwj","ababababwk","ababababwl","ababababwm","ababababwn","ababababwo","ababababwp","ababababwq","ababababwr","ababababws","ababababwt","ababababwu","ababababwv","ababababww","ababababwx","ababababwy","ababababwz","ababababxa","ababababxb","ababababxc","ababababxd","ababababxe","ababababxf","ababababxg","ababababxh","ababababxi","ababababxj","ababababxk","ababababxl","ababababxm","ababababxn","ababababxo","ababababxp","ababababxq","ababababxr","ababababxs","ababababxt","ababababxu","ababababxv","ababababxw","ababababxx","ababababxy","ababababxz","ababababya","ababababyb","ababababyc","ababababyd","ababababye","ababababyf","ababababyg","ababababyh","ababababyi","ababababyj","ababababyk","ababababyl","ababababym","ababababyn","ababababyo","ababababyp","ababababyq","ababababyr","ababababys","ababababyt","ababababyu","ababababyv","ababababyw","ababababyx","ababababyy","ababababyz","ababababza","ababababzb","ababababzc","ababababzd","ababababze","ababababzf","ababababzg","ababababzh","ababababzi","ababababzj","ababababzk","ababababzl","ababababzm","ababababzn","ababababzo","ababababzp","ababababzq","ababababzr","ababababzs","ababababzt","ababababzu","ababababzv","ababababzw","ababababzx","ababababzy","ababababzz"};
//        String[] words = new String[] {"aa", "abaa", "ababababaa", "ababababab","ababababac","ababababad"};

//        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] words = {"rain", "oath","pea","eat","oathi","oathk","oathf","oate","oathii","oathfi","oathfii"};
//        String[] words = {"rain"};

        char[][] board = {{'a','b'},
                          {'c','d'}};
        String [] words = {"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};
        List<String> result = t.findWords(board, words);
        System.out.println(result);
    }
}
