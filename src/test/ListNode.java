package test;


/*

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public ListNode newNode(int val) {
        this.next = new ListNode(val);
        return this.next;
    }

    public String toString(){
        return "val = " + val;
    }

    public String toTotalString(){
        return this.toString() + ", " + (next != null ? "next[" + next.toTotalString() + "]" : null);

    }
}
