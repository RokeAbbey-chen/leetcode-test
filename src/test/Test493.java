package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test493 {

    public int reversePairs(int[] nums) {
        RBTree tree = new RBTree();
        int count = 0;
        tree.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int c = tree.searchBig2N(nums[i]);
            tree.add(nums[i]);
            count += c;
        }
        return count;
    }

    public int reversePairsWithRBT(int[] nums) {
        RBTree tree = new RBTree();
        int count = 0;
        tree.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int c = tree.searchBig2N(nums[i]);
            tree.add(nums[i]);
            count += c;
        }
        return count;
    }

    public int reversePairsWithST(int[] nums) {
        SortTree2 tree = new SortTree2();
        int count = 0;
        tree.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int c = tree.searchBig2N(nums[i]);
            tree.add(nums[i]);
            count += c;
        }
        return count;
    }

    class SortTree {

        private static final long NIL = Long.MIN_VALUE;
        private long[] nodes;

        private int[] biggerCounts;
        private int[] counts;

        //        private boolean empty = true;
        public SortTree(int fixedLength) {
            nodes = new long[(fixedLength << 1) + 1];
            biggerCounts = new int[nodes.length];
            counts = new int[nodes.length];
            Arrays.fill(nodes, NIL);
        }

        public void add(int value) {
            int index;
            for (index = 0; index < nodes.length && nodes[index] != NIL && nodes[index] != value; ) {
                if (value > nodes[index]) {
                    biggerCounts[index]++;
                    index = (index << 1) + 2;
                } else {
                    index = (index << 1) + 1;
                }
            }
            nodes[index] = value;
            counts[index]++;
        }

        public int searchBig2N(int value) {
            int c = 0;
            long target = value;
            target <<= 1;
            int index = 0;
            while (index < nodes.length && NIL != nodes[index]) {
                if (target > nodes[index]) {
                    index = (index << 1) + 2;
                } else if (target < nodes[index]) {
                    c += counts[index] + biggerCounts[index];
                    index = (index << 1) + 1;
                } else {
                    c += biggerCounts[index];
                    break;
                }
            }
            return c;
        }
    }

    class SortTree2 {

        // 超时
        public final Node NIL = new Node(Long.MAX_VALUE, 0, 0, null, null);

        public SortTree2() {
            NIL.left = NIL.right = NIL;
        }

        public void add(int value) {
            Node curNode = NIL;
            Node lastNode;
            final byte LEFT = -1, MID = 0, RIGHT = 1;
            byte direct;
            do {
                lastNode = curNode;
                if (value < curNode.value) {
                    curNode = curNode.left;
                    direct = LEFT;
                } else if (value > curNode.value) {
                    // NIL比所有的value都大，所以root加入的时候不会进入此分支
                    curNode.biggerCount++;
                    curNode = curNode.right;
                    direct = RIGHT;
                } else {
                    curNode.count++;
                    direct = MID;
                    break;
                }
            } while (NIL != curNode);
            if (LEFT == direct) {
                lastNode.left = new Node(value, 1, 0, NIL, NIL);
            } else if (RIGHT == direct) {
                lastNode.right = new Node(value, 1, 0, NIL, NIL);
            }
        }

        public int searchBig2N(int value) {
            long target = value;
            target <<= 1;
            Node curNode = NIL;
            int biggerCount = 0;
            do {
                if (target < curNode.value) {
                    biggerCount += curNode.count + curNode.biggerCount;
                    curNode = curNode.left;
                } else if (target > curNode.value) {
                    curNode = curNode.right;
                } else {
                    biggerCount += curNode.biggerCount;
                    break;
                }
            } while (NIL != curNode);
            return biggerCount;
        }

        class Node {
            long value;
            int count;
            int biggerCount;
            Node left;
            Node right;

            public Node(long value, int count, int biggerCount, Node left, Node right) {
                this.value = value;
                this.count = count;
                this.biggerCount = biggerCount;
                this.left = left;
                this.right = right;
            }
        }
    }

    public static class RBTree {
        // AC
        Node root;
        final Node nil;

        public RBTree() {
            root = null;
            nil = new Node(Long.MAX_VALUE, null, 0);
            nil.left = nil.right = nil;
            nil.color = Node.COLOR_BLACK;
        }

        public void add(int value) {
            if (null == root) {
                Node node = new Node();
                root = node;
                node.init(value, nil, nil, nil, 1);
                node.color = Node.COLOR_BLACK;
                nil.left = node;
            } else {
                Node lastNode = nil;
                int direct = 0; // -1 代表左边 0代表相等 1代表右边
                for (Node curNode = root; nil != curNode; ) {
                    lastNode = curNode;
                    if (value < curNode.value) {
                        direct = -1;
                        curNode = curNode.left;
                    } else if (value > curNode.value) {
                        direct = 1;
                        curNode.biggerCount ++;
                        curNode = curNode.right;
                    } else {
                        direct = 0;
                        break;
                    }
                }
                if (0 == direct) {
                    lastNode.count += 1;
                } else {
                    Node node = new Node();
                    node.color = Node.COLOR_RED;
                    if (-1 == direct) {
                        lastNode.left = node;
                    } else if (1 == direct) {
                        lastNode.right = node;
                    }
                    node.init(value, nil, nil, lastNode, 1);
                    adjustNode(node);
                }

            }
        }

        public void adjustNode(Node node) {
            Node parent = node.parent;
            while (Node.COLOR_BLACK != parent.color) {
                Node pparent = parent.parent;
                Node uncle;
                Node left, middle, right;
                if (pparent.right == parent) {
                    uncle = pparent.left;
                    if (parent.left == node) {
                        // 对parent进行右旋
                        rightRotate(parent, nil);
                    }
                    // 对pparent进行左旋
                    left = leftRotate(pparent, nil);
                    middle = left.parent;
                    right = middle.right;
                } else {
                    uncle = pparent.right;
                    if (parent.right == node) {
                        leftRotate(parent, nil);
                    }
                    right = rightRotate(pparent, nil);
                    middle = right.parent;
                    left = middle.left;
                }
                if (nil == uncle) {
                    middle.color = Node.COLOR_BLACK;
                    left.color = Node.COLOR_RED;
                    right.color = Node.COLOR_RED;
                    break;
                } else {
                    left.color = right.color = Node.COLOR_BLACK;
                    node = middle;
                    parent = node.parent;
                }
            }
            root = nil.left;
            root.color = Node.COLOR_BLACK;

        }


        private static Node rightRotate(Node node, Node nil) {
            assert node.left != null && node.left != nil;
            Node parent = node.parent;
            Node left = node.left;
            if (parent.right == node) {
                parent.right = left;
            } else {
                parent.left = left;
            }
            left.parent = parent;
            node.left = left.right;
            node.left.parent = node;
            left.right = node;
            node.parent = left;

            left.biggerCount += node.count + node.biggerCount;
            return node;
        }

        private static Node leftRotate(Node node, Node nil) {
            assert node.right != null && node.right != nil;
            Node parent = node.parent;
            Node right = node.right;
            if (parent.right == node) {
                parent.right = right;
            } else {
                parent.left = right;
            }
            right.parent = parent;
            node.right = right.left;
            node.right.parent = node;
            right.left = node;
            node.parent = right;

            node.biggerCount -= right.count + right.biggerCount;
            return node;
        }


        public int searchBig2N(int value) {
            long target = value;
            target <<= 1;
            Node curNode = nil;
            int biggerCount = 0;
            do {
                if (target < curNode.value) {
                    biggerCount += curNode.count + curNode.biggerCount;
                    curNode = curNode.left;
                } else if (target > curNode.value) {
                    curNode = curNode.right;
                } else {
                    biggerCount += curNode.biggerCount;
                    break;
                }
            } while (nil != curNode);
            return biggerCount;
        }

        public int middleOrder(Node node, List<Long> list, int level) {
            if (nil == node) {
                return -1;
            }
            int tmpLevel = level;
            tmpLevel = Math.max(middleOrder(node.left, list, level + 1), tmpLevel);
            list.add(node.value);
            tmpLevel = Math.max(middleOrder(node.right, list, level + 1), tmpLevel);
            return tmpLevel;
        }

        class Node {
            static final boolean COLOR_RED = true;
            static final boolean COLOR_BLACK = false;
            long value;
            /**
             * 代表value值重复的次数
             */
            int count = 0;

            /**
             * biggerCount代表右子树包含的节点数量，不含自身。
             */
            int biggerCount = 0;

            /**
             * color 代表节点颜色，默认是红色(true), 黑色是false.
             */
            boolean color = COLOR_RED;
            Node left;
            Node right;
            Node parent;

            public Node() {

            }

            public Node(long value, Node parent, int count) {
                init(value, parent, count);
            }

            public Node(long value, Node left, Node right, Node parent, int count) {
                init(value, left, right, parent, count);
            }

            public void init(long value, Node parent, int count) {
                this.value = value;
                this.parent = parent;
                this.count = count;
            }

            public void init(long value, Node left, Node right, Node parent, int count) {
                this.init(value, parent, count);
                this.left = left;
                this.right = right;
            }

            // 别忘了给left, right赋值nill

        }
    }

    public static void main(String[] args) {
        Test493 t = new Test493();
        Random random = new Random();
        for (int i = 0; i < 10; i ++) {
            int[] nums = new int[1024 << 2];
            for (int j = 0; j < nums.length; j ++) {
                nums[j] = random.nextInt((int)1e4);
            }

//            int[] nums = {1, 5, 3, 3, 2, 3, 1, 0};
            int count = t.reversePairsWithRBT(nums);
            int count2 = t.reversePairsWithST(nums);

            System.out.println("count == count2:" + (count == count2));
            System.out.println("count: " + count + ", count2:" + count2);
            System.out.println("------------------");
        }
    }

}
