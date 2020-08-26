class client{
    public static void main(String[] args) throws Exception {
        linkedlist ll=new linkedlist();
        for(int i=1;i<15;i++){
            ll.addLast(i*10);
        }
        ll.addFirst(5);
        System.out.println("\n"+ll.size());
        System.out.println(ll.isEmpty());
        linkedlist.Node curr=ll.head;
        while(curr!=null){
            System.out.print(curr.data+" ");
            curr=curr.next;
        }
        System.out.println(ll.removeLast());
    }
}