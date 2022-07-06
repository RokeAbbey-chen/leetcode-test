package test;

import java.util.*;

//import static test.Test480.RBTree.Node.COLOR_BLACK;
//import static test.Test480.RBTree.Node.COLOR_RED;

public class Test480 {
    private static final boolean COLOR_RED = true;
    private static final boolean COLOR_BLACK = false;
    public double[] medianSlidingWindow(int[] nums, int k) {
        RBTree tree = new RBTree();
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < k - 1; i ++) {
            tree.add(nums[i]);
        }
        for (int i = k - 1; i < nums.length; i ++) {
            tree.add(nums[i]);
            if ((k & 1) == 1) {
                RBTree.Node mid0 = tree.findForIndex(k >> 1);
                result[i - k + 1] = mid0.value;
            } else {
                RBTree.Node mid0 = tree.findForIndex((k - 1) >> 1);
                mid0 = tree.findForIndex((k - 1) >> 1);
                RBTree.Node mid1 = tree.findForIndex(k >> 1);
                result[i - k + 1] = (mid0.value + 0.0 + mid1.value) / 2.0;
            }
            tree.delete(nums[i - k + 1], 1);
        }
        return result;
    }



    static class RBTree {

        public final Node root = new Node(Integer.MAX_VALUE, Node.COLOR_BLACK);
        private final boolean DIR_LEFT = false;
        private final boolean DIR_RIGHT = true;

        public Node findForIndex(int index) {
            Node node = root.left;
            int offset = 0;
            while (null != node) {
                if (index < offset + node.leftChildCount) {
                    node = node.left;
                } else if (index >= offset + node.leftChildCount + node.count) {
                    offset += node.leftChildCount + node.count;
                    node = node.right;
                } else {
                    return node;
                }
            }
            return null;
        }

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

        private void clearNode(Node node, final Node stop, int count) {
            Node parent = node.parent;
            Node puppet = node;
            count = count >= 0 ? count : node.count;
            while (stop != parent) {
                if (parent.left == puppet) {
                    parent.leftChildCount -= count;
                } else {
                    parent.rightChildCount -= count;
                }
                puppet = parent;
                parent = puppet.parent;
            }
        }
        public void delete(int v, int count) {

            Node p = getParent(new Node(v, COLOR_RED), false);
            Node node = null;
            if (null != p.left && p.left.value == v) {
                node = p.left;
            } else if (null != p.right && p.right.value == v) {
                node = p.right;
            }
            if (null != node) {
                delete(node, count);
            }

        }

        public void delete(Node node, int count) {
            clearNode(node, null, count);

            if (node.count > count){
                node.count -= count;
                return;
            }
            if (null != node.right) {
                Node next = mostLeft(node.right);
                clearNode(next, node.parent, next.count);
                node.value = next.value;
                node.count = next.count;

                if (null != next.right) {
                    next.right.parent = next.parent;
                    next.right.color = COLOR_BLACK;
                    if (next.parent.left == next) {
                        next.parent.left = next.right;
                    } else {
                        next.parent.right = next.right;
                    }
                } else {
                    deleteAdjust(next);
                    if (next.parent.left == next) {
                        next.parent.left = null;
                    } else if (next.parent.right == next) {
                        next.parent.right = null;
                    }
                }


            } else {
                if (null != node.left) {
                    node.left.parent = node.parent;
                    node.left.color = COLOR_BLACK;
                } else {
                    deleteAdjust(node);
                }

                if (node.parent.left == node) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
            }

        }

        private void deleteAdjust(Node node) {
            if (COLOR_RED == node.color) return;

            Node parent = node.parent;
            while (COLOR_BLACK == node.color && root != parent) {
                Node brother = node.brother();
                boolean flag = node == parent.left;
                if (COLOR_RED == brother.color) {
                    trans(parent, flag ? DIR_LEFT: DIR_RIGHT);
                    parent.color = COLOR_RED;
                    brother.color = COLOR_BLACK;
                    brother = node.brother();
                }


                if (null == brother.left && null == brother.right
                        || null != brother.left && null != brother.right
                        && COLOR_BLACK == brother.left.color && COLOR_BLACK == brother.right.color) {
                    brother.color = COLOR_RED;
                    if (COLOR_RED == parent.color) {
                        parent.color = COLOR_BLACK;
                        node = root.left;
                    } else {
                        node = parent;
                    }
                } else {
                    if (flag && (null == brother.right || COLOR_BLACK == brother.right.color)
                            || !flag && (null == brother.left || COLOR_BLACK == brother.left.color)) {
                        trans(brother, flag ? DIR_RIGHT: DIR_LEFT);
                        brother.color = COLOR_RED;
                        brother.parent.color = COLOR_BLACK;
                        brother = brother.parent;
                    }

                    if (flag) brother.right.color = COLOR_BLACK;
                    else brother.left.color = COLOR_BLACK;

                    brother.color = brother.parent.color;
                    brother.parent.color = COLOR_BLACK;
                    trans(brother.parent, flag ? DIR_LEFT: DIR_RIGHT);
                    node = root.left;
                }
                parent = node.parent;
            }
            root.left.color = COLOR_BLACK;


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
            return getParent(node, true);
        }
        public Node getParent(Node node, boolean needAddCount) {
            if (null != node.parent) {
                return node.parent;
            }

            Node puppet = root.left;
            Node parent = root;
            if (needAddCount)
                parent.leftChildCount ++;
            while(null != puppet) {
                if (node.value == puppet.value) {
                    break;
                }
                parent = puppet;
                if (node.value < puppet.value) {
                    if (needAddCount)
                        parent.leftChildCount ++;
                    puppet = puppet.left;
                } else {
                    if (needAddCount)
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

            public Node brother() {
                if (null == parent) return null;
                if (this == parent.left) return parent.right;
                return parent.left;
            }
            @Override
            public String toString() {
                return "v=" + value + ", c=" + (color ? 'r': 'b');
            }
        }
    }


    private static int checkBlackHeight(RBTree.Node node) throws Exception {
        if (null == node) {
            return 0;
        }
        int left = checkBlackHeight(node.left);
        int right = checkBlackHeight(node.right);
        if (left != right) {
            throw new Exception("node.value = " + node.value + ", leftC = " + left + ", rightC = " + right);
        }
        return left + (COLOR_BLACK == node.color ? 1: 0);
    }

    private static void printTreeMiddle(RBTree.Node node) {
        if (null == node) return;
        printTreeMiddle(node.left);
//        System.out.print(node.value + ", ");
        System.out.print("[" + node.value + ", " + node.count + "]" + ", ");
        printTreeMiddle(node.right);
    }

    private static void printTreeMiddle(RBTree tree) {

        printTreeMiddle(tree.root.left);
    }

    public static void main1(String[] args) throws Exception {
        Test480.RBTree tree = new Test480.RBTree();

        Random random = new Random(415);
        ArrayList<Integer> needDelete = new ArrayList<>();
        for (int i = 0; i < 200; i ++) {
            int v = random.nextInt(1000);
            tree.add(v);
            needDelete.add(v);
        }

        printTreeMiddle(tree);
        System.out.println("---------------");
        checkBlackHeight(tree.root.left);
//        tree.delete(129, 1);
//        checkBlackHeight(tree.root.left);
        for (int i = 0; i < needDelete.size(); i ++) {
            tree.delete(needDelete.get(i), 1);
            System.out.println(tree.root.left.leftChildCount + ", " + tree.root.left.rightChildCount + ", v = " + needDelete.get(i) + ", root = " + tree.root.left.value);
            System.out.println(checkBlackHeight(tree.root.left));
        }

        printTreeMiddle(tree);
        System.out.println();
    }

    public static void main(String[] args) {
        Test480 t = new Test480();
//        int[] nums = {1, 4, 2, 3};
//        int k = 4;
        Random random = new Random(4195);
        int[] nums = new int[(int)1e6];
        int k = 6;
        for (int i = 0; i < nums.length; i ++) {
//            nums[i] = random.nextInt(100);
//            System.out.print(nums[i] + ", ");
            nums[i] = nums.length - i;
        }
        System.out.println("--------------");
        long start = System.currentTimeMillis();
        double[] result = t.medianSlidingWindow(nums, k);
        long end = System.currentTimeMillis();
        System.out.println("use time:" + (end - start));
        for (int i = 0; i < result.length; i ++) {
//            System.out.print(result[i] + ", ");
        }
        System.out.println();

    }

}
