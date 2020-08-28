// 19 leet

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return null;
        ListNode c1=head;
        ListNode c2=head;
        while(n-->0){
            c1=c1.next;
        }
        if(c1==null){
            head=head.next;
            return head;
        }
        while(c1.next!=null){
            c1=c1.next;
            c2=c2.next;
        }
        c2.next=c2.next.next;
        return head;
    }
}