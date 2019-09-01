package sanjiaotie.com.goodcooder.Seventh;

/**
 * Created by yuxinfeng on 17/4/20.
 */
public class SolutionSeventh {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }

    public static void main(String []args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        ListNode g = new ListNode(4);
        a.next = b;
        b.next = c;
        e.next = f;
        f.next = g;
        addTwoNumbers(a, e);
    }



}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}