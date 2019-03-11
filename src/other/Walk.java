package other;

import java.util.*;

public class Walk {

    private static final int HEIGHT = 8;
    private static final int WIDTH = 6;
    private static final int BOMB_TIME = 3;
    private static final int REFRESH_TIME = 6;
    private static final char BOMB = '@';//炸弹
    private static final char DANGERROUS = '*';//危险区域
    private static final char NORMAL = '.';//安全点
    private static final char PROP = '1';//道具
    private static final char RED = 'r';//红包
    private static final char OBSTACLE = '#';//障碍
    private static final char PLAYER = '$';
    private static final char ERROR = '\u0000';
    private static final Map<Character, Integer> SCORES_MAP = new HashMap<>();
    /*实时游戏地图*/
    private char[] gameMap;
    /*无炸弹道具人物地图*/
    private char[] oriMap;
    private int playerIndex;
    private List<Integer> indexList;
    private Random random = new Random();
    /**
     * 本题可以转化为点A（初始点）到点B（安全结束点）的最长路径问题
     * 当炸弹爆炸时间为3时的最大安全区域如下（也就是最复杂的情况）
     *            .
     *          . . .
     *        . . . . .
     *      . . . . . . .
     *        . . . . .
     *          . . .
     *            .
     */

    public Walk(){
        gameMap = new char[HEIGHT * WIDTH];
        playerIndex = 0;
        indexList = new ArrayList<>(gameMap.length);
        for (int i = 0; i < gameMap.length; i ++){
            indexList.add(i);
        }
        initGameMap();
    }

    private void initGameMap(){
        Arrays.fill(gameMap, NORMAL);
        Collections.shuffle(indexList);
        Iterator<Integer> it = indexList.iterator();
        //初始化障碍物
        for (int i = 0; i < 5; i ++){
            gameMap[it.next()] = OBSTACLE;
            // 障碍物不不再记录在队列中
            it.remove();
        }
        oriMap = gameMap.clone();
        playerIndex = it.next();
        setBomb(it);
    }


    public static void main(String[] args) {
//        Walk walk = new Walk();
//        walk.show();
        List<String> l1 = Arrays.asList("1", "2");
        List<String> l2 = new ArrayList<>(l1);
        System.out.println(l1.get(0).equals(l2.get(0)));
        System.out.println(l1.indexOf(l2.get(1)));
    }

    public void show(){
        for (int i = 0; i < gameMap.length; i ++){
            if (i != playerIndex){
                System.out.print(gameMap[i]);
            } else {
                System.out.print(PLAYER);
            }
            System.out.print(' ');
            if (i % WIDTH == WIDTH - 1){
                System.out.println();
            }
        }
    }

    public void setBomb(){
        Collections.shuffle(indexList);
        setBomb(indexList.iterator());
    }

    private void setBomb(Iterator<Integer> it){
        for (int i = 0; i < 5; i ++){
            gameMap[it.next()] = PROP;
        }

        for (int i = 0; i < 5; i ++){
            gameMap[it.next()] = RED;
        }

        for (int i = 0; i < 10; i ++){
            int idx = it.next();
            setBomb(idx);
        }
    }

    private void setBomb(int idx){
        int[] idxs = calculateIndex(idx);
        gameMap[idx] = BOMB;
        if (getFromGameMap(idxs[0], idxs[1] + 1) == '.'){
            setToGameMap(idxs[0], idxs[1] + 1, DANGERROUS);
            setToGameMap(idxs[0], idxs[1] - 1, DANGERROUS);
            setToGameMap(idxs[0] + 1, idxs[1], DANGERROUS);
            setToGameMap(idxs[0] - 1, idxs[1], DANGERROUS);
        }
    }

    private boolean judgeBound(int r, int c){
        return r >= 0 && r < HEIGHT && c >= 0 && c < WIDTH;
    }

    private char getFromGameMap(int r, int c){
        if (judgeBound(r, c)) {
            return gameMap[r * WIDTH + c];
        }
        return ERROR;
    }

    private void setToGameMap(int r, int c, char flag){
        if (judgeBound(r, c)) {
            gameMap[r * WIDTH + c] = flag;
        }
    }

    private static int[] calculateIndex(int idx){
        return new int[]{idx / WIDTH, idx % WIDTH};
    }


    /**
     * 简要的以动态规划法求最长路径
     * @return
     */
    private List<Integer> maxBenefit(){
        /*搜索玩家坐标附近bomb_time范围的安全区域 求取最大路径*/
        int[][] dp = new int[BOMB_TIME + 1][BOMB_TIME + 1];
        int[] idxs = calculateIndex(playerIndex);
        for (int i = 0; i <= BOMB_TIME; i ++){
            char flag = getFromGameMap(idxs[0] + i, idxs[1]);
//            dp[i][0] =
            for (int j = i + 1; j <= BOMB_TIME; j ++){

            }

        }
        for (int i = BOMB_TIME; i >= 0; i --){
            for (int j = 0; j <= i; j ++){

            }
        }
        return null;
    }

    private List<Integer> longestPath(int from, int to, int limit, int[][] dp){
        int[] f = calculateIndex(from);
        int[] t = calculateIndex(to);
        return null;

    }
}
