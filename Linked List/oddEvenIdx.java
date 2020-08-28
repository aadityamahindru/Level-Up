 // leetcode 328

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode ehead=head.next;
        ListNode c1=head;
        ListNode c2=ehead;
        while(c1.next!=null&&c2.next!=null){
            c1.next=c2.next;
            c1=c1.next;
            
            c2.next=c1.next;
            c2=c2.next;
        }
        c1.next=ehead;
        return head;
    }
}