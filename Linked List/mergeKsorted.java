// leetcode 23

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        return mergeKLists_(lists,0,lists.length-1);
    }
    public ListNode mergeKLists_(ListNode[] lists,int si,int ei){
        if(si==ei){
            return lists[si];
        }
        int mid=(si+ei)/2;
        ListNode l1=mergeKLists_(lists,si,mid);
        ListNode l2=mergeKLists_(lists,mid+1,ei);
        return mergeTwoLists(l1,l2);
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null||l2==null) return l1!=null?l1:l2;
        ListNode dummy=new ListNode(-1);
        ListNode prev=dummy;
        ListNode c1=l1;
        ListNode c2=l2;
        while(c1!=null&&c2!=null){
            if(c1.val<c2.val){
                prev.next=c1;
                c1=c1.next;
            }else{
                prev.next=c2;
                c2=c2.next;
            }
            prev=prev.next;
        }
        if(c1!=null) prev.next=c1;
        if(c2!=null) prev.next=c2;
        return dummy.next;
    }
}