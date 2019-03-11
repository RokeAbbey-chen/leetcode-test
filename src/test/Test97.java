package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*
* */
public class Test97 {
    public static void main(String[] args) {
        Test97 t = new Test97();
        int n = 0;
        System.out.println(t.generateTrees(n));
    }
    public List<TreeNode> generateTrees(int n) {
        if(n <= 0) { return Arrays.asList(); }
        return helper(1, n);
    }
    private List<TreeNode> helper(int start, int end){
        List<TreeNode> list = new LinkedList<>();
        if(start > end){
            list.add(null);
            return list;
        }

        for(int cur = start; cur <= end; cur ++) {
            List<TreeNode> leftChilds = helper(start, cur - 1);
            List<TreeNode> rightChilds = helper(cur + 1, end);
            for(TreeNode left : leftChilds){
                for(TreeNode right : rightChilds){
                    TreeNode root = new TreeNode(cur);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;

    }
}
