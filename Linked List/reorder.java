// leetcode 143

class Solution {
    public void reorderList(ListNode head) {
        ListNode mid=middle(head);
        ListNode nhead=mid.next;
        mid.next=null;
        nhead=reverse(nhead);
        ListNode c1=head;
        ListNode c2=nhead;
        while(c1!=null&&c2!=null){
            ListNode f1=c1.next;
            ListNode f2=c2.next;
            c1.next=c2;
            c2.next=f1;
            c1=f1;
            c2=f2;
        }
        
    }
    public ListNode middle(ListNode head){
        ListNode fast=head;
        ListNode slow=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    public ListNode reverse(ListNode head){
        ListNode prev =null;
        ListNode curr=head;
        ListNode fwd=null;
        while(curr!=null){
            fwd=curr.next;
            curr.next=prev;
            prev=curr;
            curr=fwd;
        }
        return prev;
    }
}