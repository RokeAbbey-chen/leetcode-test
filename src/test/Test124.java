package test;

import com.sun.source.tree.Tree;

import java.util.HashMap;

public class Test124 {

    public static class TreeNode {
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

    private int maxSum = Integer.MIN_VALUE;

    private int selectMaxSum(TreeNode node) {
        if (null == node) {
            return 0;
        }

        if (null == node.left && null == node.right) {
            maxSum = Math.max(maxSum, node.val);
            return node.val;
        }

        int suml = 0, sumr = 0;

        if (null != node.left) {
            suml = selectMaxSum(node.left);
        }
        if (null != node.right) {
            sumr = selectMaxSum(node.right);
        }
        int sum = Math.max(suml, 0) + Math.max(sumr, 0); //Math.max(suml, sumr);
        sum += node.val;
        maxSum = Math.max(maxSum, sum);
        return node.val + Math.max(0, Math.max(suml, sumr));

    }

    public int maxPathSum(TreeNode root) {
        selectMaxSum(root);
        return maxSum;
    }


    public static void main0(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append('a');
        sb.append('b');
        sb.append('c');
        map.put("abc", 10);
        System.out.println(map.get(sb.toString()));
    }

    public static void main1(String[] args) {
        TreeNode root = new TreeNode(-1);
        Test124 t = new Test124();
        int result = t.maxPathSum(root);
        System.out.println(result);
    }

    public static void main(String[] args) {
        main1(args);
    }

}
