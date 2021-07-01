package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Recover Binary Search Tree
 */
public class Test99 {

    private final TreeNode SOLVED_FLAG = new TreeNode();

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    private void swap(TreeNode node1, TreeNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    /**
     * @param last
     * @param cur
     * @param next
     * @param upOrDown true 代表峰值
     * @return
     */
    public boolean check(TreeNode last, TreeNode cur, TreeNode next, boolean upOrDown) {
        if (upOrDown) {
            return cur.val > last.val && (null == next || next.val <= cur.val);
        }
        return cur.val < last.val && (null == next || next.val >= cur.val);
    }

    public void diagnose(ArrayList<TreeNode> list) {
        int up = 0, down = 0;
        boolean flagUp = false, flagDown = false;
        int size = list.size();
        for (int i = 1; i < size - 1; i++) {
            TreeNode lastNode = list.get(i - 1);
            TreeNode currentNode = list.get(i);
            TreeNode nextNode = list.get(i + 1);
            if (!flagUp && check(lastNode, currentNode, nextNode, true)) {
                up = i;
                flagUp = true;
            }

            if (!flagDown && check(lastNode, currentNode, nextNode, false)) {
                down = i;
                flagDown = true;
            }
        }

        TreeNode upNode = list.get(up), downNode = list.get(down);
        System.out.println("up:" + up + ", upNode :" + upNode.val + ", down:" + down + ", downNode:" + downNode.val);
        swap(upNode, downNode);
    }

    public void leftFirst(TreeNode root, ArrayList<TreeNode> list) {
        if (root == null) {
            return;
        }

        if (null != root.left) {
            leftFirst(root.left, list);
        }
        list.add(root);
        if (null != root.right) {
            leftFirst(root.right, list);
        }
    }

    public void printList(ArrayList<TreeNode> list) {
        int size = list.size();
        for (int i = 0; i < size; i ++) {
            TreeNode node = list.get(i);
            if (null == node) { System.out.println("null"); continue ;}
            System.out.println(node.val);
        }
    }
    public void recoverTree(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(new TreeNode(Integer.MIN_VALUE));
        leftFirst(root, list);
        list.add(new TreeNode(Integer.MAX_VALUE));
        printList(list);
        diagnose(list);
    }

    public ArrayList<TreeNode> list2Tree(List<Integer> list) {
        int size = list.size();
        ArrayList<TreeNode> nodes = new ArrayList<>(size);
        for (int i = 0; i < size; i ++) {
            Integer val = list.get(i);
            if (null == val) { nodes.add(null); continue; }
            TreeNode node = new TreeNode(val.intValue());
            nodes.add(node);
            if (i > 0 && (i & 1) == 1) {
                TreeNode parent = nodes.get((i - 1) >> 1);
                parent.left = node;
            } else if (i > 0 && (i & 1) == 0) {
                TreeNode parent = nodes.get((i - 2) >> 1);
                parent.right = node;
            }
        }
        return nodes;
    }

    private static void main1() {
        List<Integer> input = Arrays.asList(146,71,321,55,null,231,399,-13,null,null,null,null,null,-33);
//        List<Integer> input = Arrays.asList(146,71,-13,55,null,231,399,321,null,null,null,null,null,-33);
        ArrayList<TreeNode> leftFirstList = new ArrayList<>();
        Test99 test99 = new Test99();
        ArrayList<TreeNode> nodes = test99.list2Tree(input);
        test99.printList(nodes);
        test99.leftFirst(nodes.get(0), leftFirstList);
        System.out.println("------------------");
        test99.printList(leftFirstList);

    }

    public static void main(String[] args) {
        main1();
    }
}
