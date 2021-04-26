package test;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Test103 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        LinkedList<List<Integer>> result = new LinkedList<>();
        if (null == root) { return result; }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    TreeNode node = stack1.pop();
                    if (null != node.left) {
                        stack2.add(node.left);
                    }
                    if (null != node.right) {
                        stack2.add(node.right);
                    }
                    list.add(node.val);
                }
            } else {
                while (!stack2.isEmpty()) {
                    TreeNode node = stack2.pop();
                    if (null != node.right) {
                        stack1.add(node.right);
                    }

                    if (null != node.left) {
                        stack1.add(node.left);
                    }
                    list.add(node.val);
                }
            }
            result.add(list);
        }
        return result;
    }
}
