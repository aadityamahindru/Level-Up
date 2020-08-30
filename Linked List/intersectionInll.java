// leet 160

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) return null;
        ListNode tail=headA;
        while(tail.next!=null){
            tail=tail.next;
        }
        tail.next=headB;
        ListNode ans=detectCycle(headA);
        tail.next=null;
        return ans;
    }
    public ListNode detectCycle(ListNode head){
        if(head==null||head.next==null) return null;
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow) break;
        }
        if(fast!=slow) return null;
        slow=head;
        while(slow!=fast){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }
}