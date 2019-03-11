package xiaobai;

import java.util.*;

//层序遍历
public class Test8 {
    public static void solve(int[][] nodes){
        if (nodes.length == 0){
            System.out.print(1);
            return;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] node : nodes){
            ArrayList<Integer> children = map.get(node[0]);
            if (children == null){
                children = new ArrayList<>();
                map.put(node[0], children);
            }
            children.add(node[1]);
        }

        LinkedList<Integer> q = new LinkedList<>();
        q.push(1);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()){
            int h = q.removeFirst();
            sb.append(h);
            sb.append(' ');
            ArrayList<Integer> children = map.get(h);
            if (children != null){
                q.addAll(children);
            }
        }
        if (sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        int[][] nodes = new int[T - 1][];
        for (int i = 0; i < T - 1; i ++){
            nodes[i] = new int[]{input.nextInt(), input.nextInt()};
        }
        if (T == 1){ System.out.print(1); }
        else { solve(nodes); }
    }
/*
10
3 7
2 4
2 10
10 3
5 2
1 5
5 8
4 6
8 9
* */
}

