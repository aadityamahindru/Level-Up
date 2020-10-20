class DllToBst{
    static class Node{
        Node next;
        Node prev;
        int data;
        Node(int data){
            prev=null;
            next=null;
            this.data=data;
        }
        Node(int data,Node next,Node prev){
            this.prev=prev;
            this.next=next;
            this.data=data;
        }
    }
    public static Node dllToBST(Node head,Node tail){
        if(head==tail||head==null||tail==null){
            return head;
        } 
        Node mid=middle(head);

        Node prev=mid.prev;
        Node next=mid.next;

        if(prev!=null) prev.next=null;
        if(next!=null) next.prev=null;
    
        mid.prev=dllToBST(head,prev);
        mid.next=dllToBST(next,tail);
        return mid;
    }
    public static Node middle(Node head){
        if(head==null||head.next==null) return head;
        Node slow=head;
        Node fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    static Node head=null,tail=null;
    public static void addLast(int data){
        Node node=new Node(data);
        if(head==null){
            head=tail=node;
        }else{
            tail.next=node;
            node.prev=tail;
            tail=node;
        }
    }
    public static void display(Node node){
        if(node==null) return;
        StringBuilder sb = new StringBuilder();
        
        sb.append(node.prev!=null?node.prev.data+"" : ".");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.next!=null?node.next.data+"" : ".");

        System.out.println(sb.toString());

        display(node.prev);
        display(node.next);
    }
    public static void main(String[] args) {
        for(int i=1;i<=4;i++) addLast(i*10);
        Node root=dllToBST(head,tail);
        // display(root);
        System.out.println(root.prev.data+" "+root.data+" "+root.next.data);
    }


    //1339
    //1325
}