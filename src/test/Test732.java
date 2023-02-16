package test;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Test732 {
    public static class MyCalendarThree {
//        private TreeMap<Integer, Integer> tree = new TreeMap<>();
        Node root;
        Node last;
        int max = 1;
        public MyCalendarThree() {
            int inf = Integer.MAX_VALUE;
            root = new Node(-1, -1);
            last = new Node(inf, inf);
            root.next = last;
            last.prev = root;
        }

        public int book(int s0, int e0) {
            Node ptr = last;
            System.out.println("s0 = " + s0 + ", e0 = " + e0);
            while (ptr != null) {
                if (s0 <= ptr.l && e0 > ptr.l && e0 < ptr.r) {
                    // s0 l e0 r
                    remove(ptr);
                    Node node = append(ptr.prev, new Node(ptr.l, e0, ptr.c + 1));
                    append(node, new Node(e0, ptr.r, ptr.c));
                    e0 = ptr.l;
                    max = Math.max(ptr.c + 1, max);
                    ptr = node;
                    if (s0 == ptr.l)
                        break;
                } else if (s0 <= ptr.l && e0 == ptr.r) {
                    // s0 l r
                    ptr.c ++;
                    e0 = ptr.l;
                    max = Math.max(ptr.c, max);
                    if (s0 == ptr.l)
                        break;
                } else if (s0 >= ptr.l && s0 < ptr.r && e0 >= ptr.r) {
                    // l s0 r e0
                    Node node;
                    if (s0 > ptr.l) {
                        remove(ptr);
                        node = append(ptr.prev, new Node(ptr.l, s0, ptr.c));
                        node = append(node, new Node(s0, ptr.r, ptr.c + 1));
                        max = Math.max(ptr.c + 1, max);
                        if (e0 > ptr.r)
                            append(node, new Node(ptr.r, e0));
                    } else {
                        ptr.c ++;
                        if (e0 > ptr.r)
                            append(ptr, new Node(ptr.r, e0));
                        max = Math.max(ptr.c, max);
                    }
                    break;
                } else if (s0 < ptr.l && e0 > ptr.r) {
                    // s0 l r e0
                    ptr.c ++;
                    append(ptr, new Node(ptr.r, e0));
                    max = Math.max(ptr.c, max);
                    e0 = ptr.l;
                } else if (s0 > ptr.l && e0 <= ptr.r) {
                    // l s0 e0 r
                    remove(ptr);
                    Node node = append(ptr.prev, new Node(ptr.l, s0, ptr.c));
                    node = append(node, new Node(s0, e0, ptr.c + 1));
                    append(node, new Node(e0, ptr.r, ptr.c));
                    max = Math.max(ptr.c + 1, max);
                    break;
                } else if (ptr.r <= s0) {
                    append(ptr, new Node(s0, e0));
                    max = Math.max(1, max);
                    break;
                }
                ptr = ptr.prev;
            }
            for (Node ptr2 = root.next; ptr2 != last; ptr2 = ptr2.next) {
                System.out.print("[" + ptr2.l + ", " + ptr2.r + ", " + ptr2.c + "] ");
            }

            System.out.println();
            for (Node ptr2 = last.prev; ptr2 != root; ptr2 = ptr2.prev) {
                System.out.print("[" + ptr2.l + ", " + ptr2.r + ", " + ptr2.c + "] ");
            }
            System.out.println();
            System.out.println("----");
            return max;
        }

        private Node append(Node n0, Node n1) {
            Node tmp = n0.next;
            n0.next = n1;
            n1.prev = n0;
            n1.next = tmp;
            tmp.prev = n1;
            return n1;
        }

        private void remove(Node n0) {
            n0.prev.next = n0.next;
            n0.next.prev = n0.prev;
        }

        private static class Node{
            Node prev;
            Node next;
            int l, r;
            int c;
            public Node(int l, int r) {
                this.l = l;
                this.r = r;
                this.c = 1;
            }
            public Node(int l, int r, int c) {
                this.l = l;
                this.r = r;
                this.c = c;
            }
        }
    }

    public static void main(String[] args) {
//        int[][] nums = {{24,40},{43,50},{27,43},{5,21},{30,40},{14,29},{3,19},{3,14},{25,39},{6,19}};
//        int[][] nums =
//            {{47,50},{1,10},{27,36},{40,47},{20,27},{15,23},{10,18},{27,36},{17,25},{8,17},{24,33},{23,28},{21,27},{47,50},{14,21},{26,32},{16,21},{2,7},{24,33},{6,13},{44,50},{33,39},{30,36},{6,15},{21,27},{49,50},{38,45},{4,12},{46,50},{13,21}};
        int[][] nums =
                {{28,46},{9,21},{21,39},{37,48},{38,50},{22,39},{45,50},{1,12},{40,50},{31,44}};
        MyCalendarThree test = new MyCalendarThree();
        for (int i = 0; i < nums.length; i ++) {
            System.out.println(test.book(nums[i][0], nums[i][1]));
        }
    }

}
