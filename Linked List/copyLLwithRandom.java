// leetcode 138

class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) return head;
        copyNodes(head);
        setRandom(head);
        Node even=new Node(-1);
        Node odd=new Node(-1);
        return extractList(head);
        
    }
    public void setRandom(Node head){
        Node curr=head;
        while(curr!=null){
            if(curr.random!=null){
                curr.next.random=curr.random.next;
            }else{
                curr.next.random=null;
            }
            curr=curr.next.next;
        }
    }
    public void copyNodes(Node head){
        Node curr =head;
        while(curr!=null){
            Node node=new Node(curr.val);
            Node next=curr.next;
            curr.next=node;
            node.next=next;
            curr=next;
        }
    }
    public Node extractList(Node head){
        Node dummy=new Node(-1);
        
       Node c1=head;
        Node c2=head.next;
        dummy.next=c2;
        while(c1.next!=null&&c2.next!=null){
            c1.next=c2.next;
            c1=c1.next;
            c2.next=c1.next;
            c2=c2.next;
        }
        c1.next=null;
        c2.next=null;
        return dummy.next;
    }
}