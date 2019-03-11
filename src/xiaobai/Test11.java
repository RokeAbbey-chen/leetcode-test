package xiaobai;

import java.util.HashMap;

public class Test11 {

    public static void main(String[] args) {
        HashMap<Integer, Integer[]> map = new HashMap<>();
        HashMap<Integer, Integer[]> map2 = null;
        map.put(1, new Integer[]{0, 0});
        map2 = (HashMap<Integer, Integer[]>) map.clone();
        System.out.println(map2.size());
        map2.get(1)[0] ++;
        System.out.println(map.get(1)[0]);


        int[] arr = {0, 0};
        int[] arr2 = arr.clone();
        System.out.println(arr.length);
    }
}
