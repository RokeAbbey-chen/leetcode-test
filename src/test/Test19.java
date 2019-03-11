package test;

/*
* Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*
*
* */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Test19 {
    public static void main(String[] args){
        ListNode node = new ListNode(1);
        node.newNode(2)
                .newNode(3)
                .newNode(4)
                .newNode(5);
        int n = 3;
        System.out.println(new Test19().removeNthFromEnd(node, n).toTotalString());

    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;
        ListNode p1 = head;
        ListNode p2 = null;
        head = new ListNode(0);
        head.next = p1;
        p2 = head;
        int i ;
        for(i = 0; p1 != null && i < n; i ++)
            p1 = p1.next;
//        if(i < n) //这里不需要对这种情况做特殊处理， 从letcode的测试用例上看出来 当n比nodes的节点数量大的时候 直接去掉第一个
//            return head.next;
        while(p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        p2.next = p2.next.next;
        return head.next;




    }
}
