package other;

import java.util.concurrent.ConcurrentHashMap;

public class Test1 {
    public static void main(String[] args){
        System.out.println(-1 >> 10);
        System.out.println(-1 >>> 10);
        System.out.println(ECmdType.BET_IN_GAME.equals(ECmdType.getByCommand("游戏竞猜")));
    }
}
