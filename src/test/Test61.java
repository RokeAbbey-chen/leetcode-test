package test;

/*
* Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*
* */
public class Test61 {
    public static void main (String[] args){
        ListNode node = new ListNode(1);
        node.newNode(2)
                .newNode(3)
                .newNode(4)
                .newNode(5);
        int k = 3;
        node = new ListNode(1);
        k = 1;
        k = 99;

        node.newNode(2);
        k = 3;
        System.out.println(new Test61().rotateRight(node, k).toTotalString());
    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null)
            return null;
        ListNode p1 = head;
        head = new ListNode(0);
        head.next = p1;
        ListNode p2 = head;
        ListNode p3 = head;
        while(true) {
            int i;
            p1 = head.next;
            for (i = 0; p1 != null && i < k; i++)
                p1 = p1.next;
            if (i != k)
                k = k % i;
            else
                break;
        }
        while(p1 != null){
            p3 = p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        p3.next = head.next;
        head.next = p2.next;
        if(p2 != head)
            p2.next = null;
        return head.next;
    }
}
