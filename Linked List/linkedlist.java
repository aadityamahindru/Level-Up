class linkedlist{
    class Node{
        int data=0;
        Node next=null;
        Node(int data){
            this.data=data;
            next=null;
        }
    }
    Node head,tail;
    int size=0;

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    // add first
    private void addFirstNode(Node node){
        if(this.size==0){
            this.head=node;
            this.tail=node;
        }else{
            node.next=this.head;
            this.head=node;
        }
    }
    public void addFirst(int data){
        Node node=new Node(data);
        addFirstNode(node);
        this.size++;
    }
    private void addLastNode(Node node){
        if(this.size==0) addFirstNode(node);
        else{
            tail.next=node;
            tail=node;
        }
    }

    public void addLast(int data){
        Node node=new Node(data);
        addLastNode(node);
        this.size++;
    }
    private void addNodeAt(int idx,Node node){
        if(idx==this.size) addLastNode(node);
        else if(idx==0) addFirstNode(node);
        else{
            Node prev= getNodeAt(idx-1);
            Node frwd=prev.next;
            prev.next=node;
            node.next=frwd; 
        }
    }
    public void addAt(int idx,int data) throws Exception{
        if(idx<0||idx>this.size){
            throw new Exception("Null Pointer");
        }
        Node node=new Node(data);
        addNodeAt(idx,node);
        this.size++;
    }
    //==========remove=============
    private Node removeFirstNode(){
        Node rn=this.head;
        if(this.size==1){
            this.head=null;
            this.tail=null;
        }else{
            this.head=this.head.next;
            this.head.next=null;
        }
        return rn;
    }
    public int removeFirst() throws Exception{
        if(this.size==0){
            throw new Exception("Empty List");
        }
        Node rn=removeFirstNode();
        this.size--;
        return rn.data;
    }

    private Node removeLastNode(){
        if(this.size==1) return removeFirstNode();
        else{
            Node prev=getNodeAt(this.size-2);
            Node rn=prev.next;
            prev.next=null;
            return rn;
        }
    }
    public int removeLast() throws Exception{
        if(this.size==0){
            throw new Exception("Empty List");
        }
        Node rn=removeLastNode();
        this.size--;
        return rn.data;
    }
    private Node removeNodeAt(int idx){
        if(idx==this.size-1) return removeLastNode();
        else if(idx==0) return removeFirstNode();
        else{
            Node prev=getNodeAt(idx-1);
            Node node=prev.next;
            prev.next=node.next;
            node.next=null;
            return node;
        }
    }
    public int removeAt(int idx) throws Exception{
        if(idx<0||idx>=this.size){
            throw new Exception("Null Pointer");
        }
        Node rn=removeNodeAt(idx);
        this.size--;
        return rn.data;
    }
    // get==========
    public int getFirst() throws Exception{
        if(this.size==0){
            throw new Exception("Empty List");
        }
        return this.head.data;
    }
    private Node getNodeAt(int idx){
        Node node=this.head;
        while(idx-->0) node=node.next;
        return node;
    }
    public int getAt(int idx) throws Exception{
        if(idx<0||idx>=this.size){
            throw new Exception("Null Pointer");
        }
        return getNodeAt(idx).data;
    }
}