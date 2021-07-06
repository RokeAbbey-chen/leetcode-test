package datastruct;
//import static java.io.File.*;

import java.util.*;

public class RBTree2 {

    Node root;
    final Node nil;

    public RBTree2() {
        root = null;
        nil = new Node(0, null, 0);
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
                    curNode = curNode.right;
                } else {
                    direct = 0;
                    break;
                }
            }
            if (0 == direct) {
                lastNode.nodeCount += 1;
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
        return node;
    }


    public int middleOrder(Node node, List<Integer> list, int level) {
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
        int value;
        /**
         * nodeCount代表该子树所包含的节点数量, 含自身
         */
        int nodeCount = 0;

        /**
         * color 代表节点颜色，默认是红色(true), 黑色是false.
         */
        boolean color = COLOR_RED;
        Node left;
        Node right;
        Node parent;

        public Node() {

        }

        public Node(int value, Node parent, int nodeCount) {
            init(value, parent, nodeCount);
        }

        public Node(int value, Node left, Node right, Node parent, int nodeCount) {
            init(value, left, right, parent, nodeCount);
        }

        public void init(int value, Node parent, int nodeCount) {
            this.value = value;
            this.parent = parent;
            this.nodeCount = nodeCount;
        }

        public void init(int value, Node left, Node right, Node parent, int nodeCount) {
            this.init(value, parent, nodeCount);
            this.left = left;
            this.right = right;
        }

        // 别忘了给left, right赋值nill

    }

    public static void main0(String[] args) {
        RBTree2 tree = new RBTree2();
        Random random = new Random();
        final int NUM = 10000;
        ArrayList<Integer> list = new ArrayList<>(NUM + 10);
        ArrayList<Integer> list2 = new ArrayList<>(NUM + 10);
        for (int i = 0; i < NUM; i ++) {
            int v = i; //random.nextInt();
            list.add(v);
            tree.add(v);
        }
        Collections.sort(list);
        int maxLevel = tree.middleOrder(tree.root, list2, 0);
        System.out.println("maxLevel = " + maxLevel);

        boolean flag = true;
        for (int i = 0; i < NUM; i ++) {
            if (!list.get(i).equals(list2.get(i))) {
                flag = false;
                System.out.println("not equal i = " + i);
                break;
            }
        }
        if (flag) {
            System.out.println("all equal");
        }
    }

    public static void main1(String[] args) {

        int[] nums = {1, 5, 3, 3, 2, 3, 1, 0};
        RBTree2 tree = new RBTree2();
        for (int i = 0; i < nums.length; i ++) {
            tree.add(i);
        }
        System.out.println(tree);
    }

    public static void main(String[] args) {
        main1(args);
    }
}
