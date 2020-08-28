// leetcode 61

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||head.next==null||k==0) return head; 
        int len=0;
        ListNode c1=head;
        ListNode c2=head;
        while(c1!=null){
            len++;
            c1=c1.next;
        }
        c1=head;
        k=k%len;
        while(k-->0){
            c1=c1.next;
        }
        while(c1.next!=null){
            c1=c1.next;
            c2=c2.next;
        }
        c1.next=head;
        head=c2.next;
        c2.next=null;
        return head;
    }
}