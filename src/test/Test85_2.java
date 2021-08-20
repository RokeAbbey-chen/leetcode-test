package test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Test85_2 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        int[][] heights = new int[2][matrix[0].length];
        for (int j = 0; j < matrix[0].length; j ++) {
            heights[0][j] = matrix[0][j];
        }

        int res = 0;
        for (int i = 1; i < matrix.length; i ++){
            for (int j = 0; j < matrix[i & 1].length; j ++) {
                if ('1' == matrix[i & 1][j])
                    heights[i & 1][j] = heights[(i - 1) & 1][j] + 1;
                else heights[i & 1][j] = 0;
            }
            res = Math.max(res, largestRectangleArea(heights[i & 1]));
        }
        return res;
    }

    public int largestRectangleArea(int[] heights) {
        SegTree segTree = new SegTree(heights);
        LinkedList<int[]> queue = new LinkedList<>();
        int[] pair = new int[]{0, heights.length - 1};
        queue.add(pair);
        int maxArea = 0;
        while (!queue.isEmpty()) {
            pair = queue.removeFirst();
            List<Integer> heightIndexes = segTree.findMinIndexes(pair[0], pair[1]);
            maxArea = Math.max(heights[heightIndexes.get(0)] * (pair[1] - pair[0] + 1), maxArea);
            int start = pair[0];
            int end = -1;

            Iterator<Integer> iter = heightIndexes.iterator();
            assert heightIndexes.size() >= 1;
            int[] pair2;

            while (iter.hasNext()){
                end = iter.next();
                if (start <= end - 1) {
                    pair2 = new int[]{start, end - 1};
                    queue.addLast(pair2);
                    start = end + 1;
                } else {
                    start = end + 1;
                }
            }
            if (end == -1) continue;
            start = end + 1;
            end = pair[1];
            if (start > end) continue;
            pair2 = new int[]{start, end};
            queue.addLast(pair2);
        }
        return maxArea;
    }

    static class SegTree {
        SegTree.Node root;
        int[] values;
        public SegTree(int[] values) {
            root = constructMinValueSegTree(values, 0, values.length - 1);
            this.values = values;
        }

        public SegTree.Node constructMinValueSegTree(int[] values, int start, int end) {
            if (start >= end) {
                return new SegTree.Node(values[start], start);
            }

            int mid = (start + end) >> 1;
            SegTree.Node leftNode = constructMinValueSegTree(values, start, mid);
            SegTree.Node rightNode = constructMinValueSegTree(values,mid + 1, end);
            SegTree.Node self = new SegTree.Node();
            SegTree.Node tmp;
            if (leftNode.value <= rightNode.value) tmp = leftNode;
            else tmp = rightNode;
            self.value = tmp.value;
            self.addAllIndexes(tmp);

            if (leftNode.value == rightNode.value) {
                self.addAllIndexes(rightNode);
            }

            self.left = leftNode;
            self.right = rightNode;
            return self;
        }

        public List<Integer> findMinIndexes(int start, int end) {
            return findMinIndexes(start, end, root, 0, values.length - 1);
        }

        public List<Integer> findMinIndexes(int start, int end, SegTree.Node node, int nodeStart, int nodeEnd) {
            if (start <= nodeStart && end >= nodeEnd) {
                return node.indexes;
            }
            int mid = (nodeStart + nodeEnd) >> 1;
            if (start > mid) {
                return findMinIndexes(start, end, node.right, mid + 1, nodeEnd);
            }
            if (end <= mid) {
                return findMinIndexes(start, end, node.left, nodeStart, mid);
            }

            List<Integer> leftMinIndexes, rightMinIndexes;
            leftMinIndexes = findMinIndexes(start, mid, node.left, nodeStart, mid);
            rightMinIndexes = findMinIndexes(mid + 1, end, node.right, mid + 1, nodeEnd);
            int leftIndex = leftMinIndexes.get(0);
            int rightIndex = rightMinIndexes.get(0);
            if (values[leftIndex] < values[rightIndex]) {
                return leftMinIndexes;
            } else if (values[leftIndex] > values[rightIndex]) {
                return rightMinIndexes;
            } else {
                LinkedList<Integer> tmp = new LinkedList<>();
                tmp.addAll(leftMinIndexes);
                tmp.addAll(rightMinIndexes);
                return tmp;
            }
        }

        static class Node {
            int value;
            List<Integer> indexes = new LinkedList<>();
            SegTree.Node left;
            SegTree.Node right;

            public Node() {

            }

            public Node(int value, int selfIndex) {
                this(value, selfIndex, null, null);
            }

            public Node(int value, int selfIndex, SegTree.Node left, SegTree.Node right) {
                this.value = value;
                this.left = left;
                this.right = right;
                indexes.add(selfIndex);
            }

            public void addIndex(int index) {
                if (indexes == null) {
                    indexes = new LinkedList<>();
                }
                indexes.add(index);
            }

            public void addAllIndexes(List<Integer> indexes) {
                if (this.indexes == null) {
                    indexes = new LinkedList<>();
                }
                this.indexes.addAll(indexes);
            }

            public void addAllIndexes(SegTree.Node node) {
                addAllIndexes(node.indexes);
            }
        }
    }

//    public int getMax
//    public int getMaxArea(int[] heights) {
//        int[] stack = new int[heights.length];
//        int ids = 0;
//        int res = 0;
//        for (int i = 0; i < heights.length; i ++) {
//            while (ids > 0 && heights[stack[ids - 1]] >= heights[i]) {
//                res = Math.max(res, (i - stack[ids - 1] + 1) * heights[i]);
//                ids --;
//            }
//            stack[ids ++ ] = [i];
//        }
//        return res;
//    }

}
