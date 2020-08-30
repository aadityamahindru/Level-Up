// leetcode 92

class Solution {
    ListNode th=null,tt=null;
    public ListNode reverseBetween(ListNode head, int n, int m) {
        ListNode curr=head,prev=null;
        int i=1;
        while(curr!=null){
            while(i>=n&&i<=m){
                ListNode next=curr.next;
                curr.next=null;
                addFirst(curr);
                curr=next;
                i++;
            }
            if(i>m){
                if(prev!=null){
                    prev.next=th;
                    tt.next=curr;
                }else{
                    head=th;
                    tt.next=curr;
                }
                break;
            }
            prev=curr;
            curr=curr.next;
            i++;
        }
        return head;
    }
    public void addFirst(ListNode node){
        if(th==null){
            th=node;
            tt=node;
        }else{
            node.next=th;
            th=node;
        }
    }
}