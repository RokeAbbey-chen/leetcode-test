package test;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Test84 {
    public int largestRectangleArea(int[] heights) {
        SegTree segTree = new SegTree(heights);
        Stack<int[]> stack = new Stack<>();
        int[] pair = new int[]{0, heights.length - 1};
        stack.add(pair);
        int maxArea = 0;
        while (!stack.isEmpty()) {
            pair = stack.pop();
            System.out.println("pop:" + pair[0] + ", " + pair[1]);
            List<Integer> heightIndexes = segTree.findMinIndexes(pair[0], pair[1]);
            maxArea = Math.max(heightIndexes.get(0) * (pair[1] - pair[0] + 1), maxArea);
            int start = 0;
            int end = -1;
            Iterator<Integer> iter = heightIndexes.iterator();
            assert heightIndexes.size() >= 1;
            int[] pair2;
            while (iter.hasNext()){
                end = iter.next();
                pair2 = new int[]{start, end};
                System.out.println("in push start:" + stack + ", end:" + end);
                stack.push(pair2);
                start = end;
            }
            if (end == -1) {
                continue;
            }
            start = end + 1;
            end = pair[1];
            if (start > end) {
                continue;
            }
            pair2 = new int[]{start, end};
            System.out.println("out push start:" + stack + ", end:" + end);
            stack.push(pair2);
        }
        return maxArea;
    }

    static class SegTree {
        Node root;
        int[] values;
        public SegTree(int[] values) {
            root = constructMinValueSegTree(values, 0, values.length - 1);
            this.values = values;
        }

        public Node constructMinValueSegTree(int[] values, int start, int end) {
            if (start >= end) {
                return new Node(values[start], start);
            }

            int mid = (start + end) >> 1;
            Node leftNode = constructMinValueSegTree(values, start, mid);
            Node rightNode = constructMinValueSegTree(values,mid + 1, end);
            Node self = new Node();
            Node tmp;
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

        public List<Integer> findMinIndexes(int start, int end, Node node, int nodeStart, int nodeEnd) {
            if (start <= nodeStart && end >= nodeEnd) {
                return node.indexes;
            }
            int mid = (nodeStart + nodeEnd) >> 1;
            if (start > mid) {
                return findMinIndexes(start, end, node.right, mid + 1, nodeEnd);
            }
            if (end <= mid) {
                return findMinIndexes(start, end, node.left, nodeStart, end);
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
                System.err.println("left eq right  error!!!");
                return null;
            }
        }

        static class Node {
            int value;
            List<Integer> indexes = new LinkedList<>();
            Node left;
            Node right;

            public Node() {

            }

            public Node(int value, int selfIndex) {
                this(value, selfIndex, null, null);
            }

            public Node(int value, int selfIndex, Node left, Node right) {
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

            public void addAllIndexes(Node node) {
                addAllIndexes(node.indexes);
            }
        }
    }

    public static void main1() {
        int[] values = {3, 1, 2, 9, 4, 2, 1, 2, 5, 7, 7 , 12, 9, 97, 88, 54, 32, 12};
//        int []values = {7, 6, 1, 8, 2, 3, 9, 5};
        Test84.SegTree tree = new Test84.SegTree(values);
        System.out.println(tree.findMinIndexes(0, values.length - 1));
        System.out.println(tree.findMinIndexes(11, 15));
    }

    public static void main2() {
        int[] values = {3, 1, 2, 9, 4, 2, 1, 2, 5, 7, 7 , 12, 9, 97, 88, 54, 32, 12};
//        int []values = {7, 6, 1, 8, 2, 3, 9, 5};
        Test84 test84 = new Test84();
        System.out.println(test84.largestRectangleArea(values));
    }
    public static void main(String[] args) {
        main2();
    }
}
