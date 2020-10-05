class middleStack{
    private class Node{
        int val;
        Node prev;
        Node next;
        Node(int val){
            this.val=val;
            this.next=null;
            this.prev=null;
        }
    }
    private Node top=null;
    private Node mid=null;
    private int size=0;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node curr=this.top;
        while(curr!=null){
            sb.append(curr.val);
            if(curr.next != null) sb.append(",");
            curr=curr.next;
        }
        sb.append("]");
        return sb.toString();
    }
    public void push(int val){
        Node node=new Node(val);
        this.size++;
        if(this.size==1){
            top=node;
            mid=node;
        }else{
            node.next=top;
            top.prev=node;
            top=node;
            if(this.size%2!=0) mid=mid.prev;
        }
    }
    public int pop(){
        if(this.size==0) return -1;
        this.size--;
        Node rm=this.top;
        if(this.size==0){
            top=null;
            mid=null;
        }else{
            top=top.next;
            top.prev=null;
            rm.next=null;
            if(this.size%2==0) mid=mid.next;
        }
        return rm.val;
    }
    public int findMiddle(){
        if(this.size==0) return -1;
        return mid.val;
    }
    public int removeMiddle(){
        if(this.size==0) return -1;
        Node rm=mid;
        if(mid==top){
            top=null;
            mid=null;
        }else{
            Node next=rm.next;
            Node prev=rm.prev;
            prev.next=next;
            next.prev=prev;
            rm.next=null;
            rm.prev=null;
            if(this.size%2==0){
                mid=prev;
            }else{
                mid=next;
            }
        }
        this.size--;
        return rm.val;
    }
}