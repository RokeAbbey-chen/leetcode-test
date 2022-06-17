package test;

import java.util.*;

import static test.Test480.RBTree.Node.COLOR_BLACK;
import static test.Test480.RBTree.Node.COLOR_RED;

public class Test480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        Integer[] sortedIndexes = new Integer[k];

        for (int i = 0; i < k; i++) sortedIndexes[i] = i;
        Comparator<Integer> cmp = (o1, o2) -> {
            if (nums[o1] < nums[o2]) return -1;
            if (nums[o1] > nums[o2]) return 1;
            return 0;
        };

        HashMap<Integer, Integer> counts = new HashMap<>();
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        return null;
    }



    static class RBTree {

        public final Node root = new Node(Integer.MAX_VALUE, Node.COLOR_BLACK);
        private final boolean DIR_LEFT = false;
        private final boolean DIR_RIGHT = true;


        public Node add(int v) {
            Node node = new Node(v, COLOR_RED);
            Node parent = getParent(node);

            if (node.value < parent.value || parent == root) {
                if (null != parent.left) {parent.left.count ++;}
                else {parent.left = node;}
                node = parent.left;
            } else if (node.value > parent.value) {
                if (null != parent.right) {parent.right.count ++;}
                else {parent.right = node;}
                node = parent.right;
            }
            node.parent = parent;
            if (root == parent) {
                node.color = COLOR_BLACK;
            } else {
                adjust(node);
            }
            return node;
        }

        public void adjust(Node node) {
            Node parent = node.parent;
            while(null != parent && root != parent &&
                    parent.color == COLOR_RED && node.color == COLOR_RED) {
                Node pparent = parent.parent;
                if (pparent.left == parent){
                    if (parent.right == node) {
                        trans(parent, DIR_LEFT);
                        node = parent;
                        parent = node.parent;
                    }
                    trans(pparent, DIR_RIGHT);
                } else {
                    if (parent.left == node) {
                        trans(parent, DIR_RIGHT);
                        node = parent;
                        parent = node.parent;
                    }
                    trans(pparent, DIR_LEFT);
                }
                node.color = COLOR_BLACK;
                node = parent;
                parent = node.parent;
            }

            root.left.color = COLOR_BLACK;
        }

        private void clearNode(Node node, final Node stop) {
            Node parent = node.parent;
            Node puppet = node;
            while (stop != parent) {
                if (parent.left == puppet) {
                    parent.leftChildCount -= node.count;
                } else {
                    parent.rightChildCount -= node.count;
                }
                puppet = parent;
                parent = puppet.parent;
            }
        }

        public void delete(Node node) {
            clearNode(node, null);
            Node removed = null;
            if (null != node.right) {
                Node next = mostLeft(node.right);
                clearNode(next, node.parent);
                node.value = next.value;
                node.count = next.count;
                if (next.parent.left == next) {
                    next.parent.left = next.right;
                } else {
                    next.parent.right = next.right;
                }
                if (null != next.right) {
                    next.right.parent = next.parent;
                }
                removed = next;

            } else {
                if (null != node.left) {
                    assert COLOR_BLACK == node.color;
                    node.left.parent = node.parent;
                    node.left.color = node.color;
                } else removed = node;

                if (node.parent.left == node) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
            }

            if (COLOR_BLACK == removed.color) {

            }



        }

        public Node mostLeft(Node node) {
            Node parent = node;
            while (null != node) {
                parent = node;
                node = node.left;
            }

            return parent;
        }
        public static void trans(Node center, boolean dir) {
            Node parent = center.parent;
            if (dir) {
                Node left = center.left;
                assert left != null;
                center.parent = left;
                left.parent = parent;
                center.left = left.right;
                center.leftChildCount = left.rightChildCount;
                left.rightChildCount = center.count + center.leftChildCount + center.rightChildCount;
                left.right = center;
                if (null != center.left) {
                    center.left.parent = center;
                }
                if (parent.left == center) {
                    parent.left = left;
                } else {
                    parent.right = left;
                }

            } else {
                Node right = center.right;
                assert right != null;
                center.parent = right;
                right.parent = parent;
                center.right = right.left;
                center.rightChildCount = right.leftChildCount;
                right.leftChildCount = center.count + center.leftChildCount + center.rightChildCount;
                right.left = center;
                if (null != center.right) {
                    center.right.parent = center;
                }
                if (parent.left == center) {
                    parent.left = right;
                } else {
                    parent.right = right;
                }
            }

        }

        public Node getParent(Node node) {
            if (null != node.parent) {
                return node.parent;
            }

            Node puppet = root.left;
            Node parent = root;
            parent.leftChildCount ++;
            while(null != puppet) {
                if (node.value == puppet.value) {
                    break;
                }
                parent = puppet;
                if (node.value < puppet.value) {
                    parent.leftChildCount ++;
                    puppet = puppet.left;
                } else {
                    parent.rightChildCount ++;
                    puppet = puppet.right;
                }
            }
            return parent;
        }


        public static class Node {
            public static final boolean COLOR_RED = true;
            public static final boolean COLOR_BLACK = false;


            public Node left;
            public Node right;
            public Node parent;

            public int count;
            public int value;
            public int leftChildCount;
            public int rightChildCount;
            public boolean color;

            public Node(int v, boolean co) {
                value = v;
                color = co;
                count = 1;
            }

            @Override
            public String toString() {
                return "v=" + value + ", c=" + (color ? 'r': 'b');
            }
        }
    }



    private static void printTreeMiddle(RBTree.Node node) {
        if (null == node) return;
        printTreeMiddle(node.left);
        System.out.print(node.value + ", ");
//        if (node.parent.value == Integer.MAX_VALUE){
//            System.out.println("---");
//        }
        printTreeMiddle(node.right);
    }

    private static void printTreeMiddle(RBTree tree) {

        printTreeMiddle(tree.root.left);
    }

    public static void main(String[] args) {
        Test480.RBTree tree = new Test480.RBTree();

        Random random = new Random(415);
        for (int i = 0; i < 200; i ++) {
            tree.add(random.nextInt(1000));
        }

        printTreeMiddle(tree);
        System.out.println();
    }

}
