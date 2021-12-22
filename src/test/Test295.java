package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test295 {
    public static class MedianFinder {
        private boolean added = false;
        private double lastMedian = Integer.MAX_VALUE;
        private List<Integer> nums;
        private RBTree2 tree;
        public MedianFinder() {
            tree = new RBTree2();
            nums = new ArrayList<>();
        }

        public void addNum(int num) {
            added = true;
            tree.add(num);
            nums.add(num);
        }

        public double findMedian() {
            if (!added) {
                return lastMedian;
            }

            int totalCount = tree.root.sonCount;
            if ((totalCount & 1) == 0) {
                lastMedian = (find(totalCount - 1 >> 1) + find(totalCount >> 1)) / 2.0;
            } else {
                lastMedian = find(totalCount - 1 >> 1);
            }
            return lastMedian;

        }

        public double findMedian2() {
            Collections.sort(nums);
            if ((nums.size() & 1) == 0) {
                int index = nums.size() - 1 >> 1;
                return (nums.get(index) + nums.get(index + 1)) / 2.0;
            } else {
                int index = nums.size() - 1 >> 1;
                return nums.get(index);
            }
        }

        /**
         *
         * @param target 注意，target是目标的下标。
         * @return
         */
        private int find(int target) {

            RBTree2.Node node = tree.root.sons[0];
            while (true) {
                int leftTotalCount = node.sons[0] != null ? node.sons[0].count + node.sons[0].sonCount: 0;
                if (target < leftTotalCount) {
                    node = node.sons[0];
                } else if (target >= leftTotalCount + node.count) {
                    target -= leftTotalCount + node.count;
                    node = node.sons[1];
                } else {
                    return node.value;
                }
            }

        }
    }

    private static class RBTree {
        private Node root = new Node(Integer.MAX_VALUE, 1, Node.COLOR_BLACK, null);
        public RBTree() {

        }

        public void add(int number) {
            Node node = root;
            Node parent = root;
            boolean bLeft = false; // true为左
            while (node != null) {
                if (number < node.value) {
                    parent = node;
                    parent.sonCount ++;
                    node = node.left;
                    bLeft = true;
                } else if (number > node.value) {
                    parent = node;
                    parent.sonCount ++;
                    node = node.right;
                    bLeft = false;
                } else {
                    node.count ++;
                    break;
                }
            }

            if (null == node) {
                node = new Node(number, 1, Node.COLOR_RED, parent);
                if (bLeft) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
                adjust(node);
            }

        }

        private void adjust(Node node) {
            while (node != root && node != null) {
                if (node == root.left) {
                    node.color = Node.COLOR_BLACK;
                    break;
                }

                Node parent = node.parent;
                Node pparent = parent.parent;

                if (Node.COLOR_RED == parent.color) {
                    if (pparent.left == parent && parent.right == node) {
                        /*一左一右*/
                        parent = translate(parent, true);
                        node = parent.left;
                    } else if (pparent.right == parent && parent.left == node) {
                        /*一右一左*/
                        parent = translate(parent, false);
                        node = parent.right;
                    }
                    node.color = Node.COLOR_BLACK;
                    node = translate(pparent, pparent.left != parent);
                } else {
                    break;
                }
            }
        }

        /**
         *
         * @param node 以node为中心，向某个方向转
         * @param director 向左转为true
         */
        private Node translate(Node node, boolean director) {
            Node parent = node.parent;
            if (director) {
                Node right = node.right;
                assert right != null;
                if (node == parent.left) parent.left = right;
                else parent.right = right;
                right.parent = parent;

                node.sonCount -= right.count + right.sonCount;
                if (right.left != null) {
                    node.sonCount += right.left.count + right.left.sonCount;
                    right.sonCount -= right.left.count + right.left.sonCount;
                    right.left.parent = node;
                }
                right.sonCount += node.count + node.sonCount;
                node.right = right.left;
                right.left = node;
                node.parent = right;
                return right;
            } else {
                Node left = node.left;
                assert left != null;
                if (parent.right == node) parent.right = left;
                else parent.left = node.left;
                left.parent = parent;

                node.sonCount -= left.count + left.sonCount;
                if (left.right != null) {
                    left.sonCount -= left.right.sonCount + left.right.count;
                    node.sonCount += left.right.sonCount + left.right.count;
                    left.right.parent = node;
                }
                left.sonCount += node.count + node.sonCount;
                node.left = left.right;
                left.right = node;
                node.parent = left;
                return left;
            }
        }

        private static class Node {
            static final boolean COLOR_BLACK = false;
            static final boolean COLOR_RED = true;
            Node left;
            Node right;
            Node parent;
            int value;
            int sonCount;
            int count;
            boolean color;

            public Node(int value, int count, boolean color, Node parent) {
                this.value = value;
                this.sonCount = 0;
                this.count = count;
                this.color = color;
                this.parent = parent;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "value=" + value +
                        ", sonCount=" + sonCount +
                        ", count=" + count +
                        ", color=" + color +
                        '}';
            }
        }
    }

    private static class RBTree2 {

        private Node root = new Node(Integer.MAX_VALUE, 1, Node.BLACK, null);

        public void add(int number) {

            Node node = root;
            Node parent = root;
            byte dir = 0;
            while (null != node) {
                parent = node;
                if (number < node.value) {
                    parent.sonCount ++;
                    node = node.sons[0]; dir = 0;
                } else if (number > node.value){
                    parent.sonCount ++;
                    node = node.sons[1]; dir = 1;
                } else {
                    node.count ++; break;
                }
            }
            if (null == node) {
                node = new Node(number, 1, Node.RED, parent);
                parent.sons[dir] = node;
                adjust(node);
            }
        }

        private void adjust(Node node) {
            for (Node p, pp;node != root;) {
                if (root.sons[0] == node) {node.color = Node.BLACK;break;}

                p = node.sons[2];
                if (Node.RED != p.color) break;

                pp = p.sons[2];
                if ((p == pp.sons[0]) != (node == p.sons[0])) {
                    // 一左一右或者一右一左 需要转枝
                    byte dir = p.sons[1] == node ? (byte)0: (byte)1;
                    p = translate(p, dir);
                    node = p.sons[dir];
                }

                node.color = Node.BLACK;
                byte dir = pp.sons[0] == p ? (byte)1: (byte)0;
                node = translate(pp, dir);

            }

        }

        /**
         *
         * @param node 以node为中心转枝
         * @param dir 向左转为0, 向右为1
         * @return 返回新的旋转中心
         */
        public Node translate(Node node, byte dir) {
            Node parent = node.sons[2];
            Node son = node.sons[1 - dir];
            son.sons[2] = parent;
            parent.sons[parent.sons[1] == node ? 1: 0] = son;

            node.sonCount -= son.count + son.sonCount;
            if (null != son.sons[dir]) {
                node.sonCount += son.sons[dir].count + son.sons[dir].sonCount;
                son.sonCount -= son.sons[dir].count + son.sons[dir].sonCount;
                son.sons[dir].sons[2] = node;
            }
            node.sons[1 - dir] = son.sons[dir];
            son.sonCount += node.count + node.sonCount;
            son.sons[dir] = node;
            node.sons[2] = son;
            return son;
        }

        public static class Node {
            public static final byte LEFT = 0;
            public static final byte RIGHT = 1;
            public static final boolean BLACK = false;
            public static final boolean RED = true;

            public Node[] sons;
            public boolean color;
            public int value;
            public int count;
            public int sonCount;


            public Node(int value, int count, boolean color, Node parent) {
                this.sons = new Node[3];
                this.sons[2] = parent;
                this.value = value;
                this.count = count;
                this.color = color;
                this.sonCount = 0;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "color=" + color +
                        ", value=" + value +
                        ", count=" + count +
                        ", sonCount=" + sonCount +
                        '}';
            }

            public String theWholeTree() {
                String sonsString = sons[0] != null ? ", left:" + sons[0].theWholeTree(): "";
                sonsString += sons[1] != null ? ", right:" + sons[1].theWholeTree(): "";
                return "{" + "color:" + color + ", value:" + value + ", count:" + count
                        + ", sonCount:" + sonCount + sonsString + "}";
            }
        }

    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        Random random = new Random(1);
        ArrayList<Integer> inps = new ArrayList<>();
        for (int i = 0; i < 100; i ++) {
            if (17 == i) {
                System.out.println("313");
            }
            int v = random.nextInt(100);
            inps.add(v);
            finder.addNum(v);
            System.out.println("ans:" + finder.findMedian() + ", " + finder.findMedian2());
        }
//        System.out.println(finder.tree.root.theWholeTree());
        System.out.println(finder.findMedian());
        finder.find(7);


    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */