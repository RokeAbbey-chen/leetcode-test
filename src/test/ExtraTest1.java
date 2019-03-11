package test;

import java.util.*;

public class ExtraTest1 {
    private static int max = 0;
    private static Set<Integer> count = new HashSet<>();
    public static void main(String[] args){
        int n = -1, m = -1;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()){ n = scanner.nextInt();}
        if (scanner.hasNextInt()){ m = scanner.nextInt();}
        int[][] w = new int[ n + 1 ][ n + 1];
        for(int i = 0; i < m; i ++){
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int v3 = scanner.nextInt();
            w[v1][v2] = v3;
//            System.out.println("v1 = " + v1 + " v2 = " + v2 + "w = " + w[v1][v2]);
        }
        ArrayList<ArrayList<Integer>> keyRoutes = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        ds(w, path, keyRoutes, 1, 0);
        printKeyRoutes(keyRoutes);
    }
    private static void ds(int[][] w, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> keyRoutes, int index, int sum){
        //flag 是否为末尾节点
        boolean flag = true;
        if(count.contains(index)){ return; }
        count.add(index);
        path.add(index);
        for(int i = 0; i < w[index].length; i ++){
            if(w[index][i] > 0 ){
                flag = false;
                ds(w, path, keyRoutes, i, sum + w[index][i]);
            }
        }
        if(flag && sum > max){ keyRoutes.clear(); max = sum; }
        if(flag && sum >= max){ keyRoutes.add((ArrayList<Integer>) path.clone()); }
        count.remove(index);
        path.remove((Integer)index);
    }

    private static void printKeyRoutes(ArrayList<ArrayList<Integer>> keyRoutes){
        for(ArrayList<Integer> a : keyRoutes){
            int last = -1;
            for(int i : a ){
                if(last != -1){ System.out.println(last + "->" + i);}
                last = i;
            }
            System.out.println("****************");
        }
    }
}

//7 8
//1 2 4
//1 3 3
//2 4 5
//3 4 3
//4 5 1
//4 6 6
//5 7 5
//6 7 2


//7 8
//1 2 4
//1 3 3
//2 4 5
//3 4 6
//4 5 1
//4 6 6
//5 7 5
//6 7 2