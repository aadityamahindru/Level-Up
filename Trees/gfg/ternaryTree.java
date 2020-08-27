class ternaryTree{
   static class Node 
{ 
    int data; 
    Node left,middle,right; 
    public Node(int data) 
    { 
        this.data = data; 
        left = middle = right = null; 
    } 
} 
    public static void main(String args[]) 
    {
        Node root = new Node(30); 
        root.left = new Node(5); 
        root.middle = new Node(11); 
        root.right = new Node(63); 
        root.left.left = new Node(1); 
        root.left.middle = new Node(4); 
        root.left.right = new Node(8); 
        root.middle.left = new Node(6); 
        root.middle.middle = new Node(7); 
        root.middle.right = new Node(15); 
        root.right.left = new Node(31); 
        root.right.middle = new Node(55); 
        root.right.right = new Node(65); 
        treeToDll(root);
        while(head!=null){
            System.out.print(head.data+" -> ");
            boolean ans= head.middle==null;
            System.out.print(" "+ans+" ");
            head=head.right;
        }
        System.out.println();
        while(tail!=null){
            System.out.print(tail.data+" <- ");
            tail=tail.left;
        }
    } 
     static Node head=null;
     static Node tail=null;
    public static void treeToDll(Node node){
        if(node==null) return;
        Node left=node.left;
        Node right=node.right;
        Node mid=node.middle;
        if(tail==null){
            head=node;
            head.left=null;
        }else{
            tail.right=node;
            node.left=tail;
        }
        node.middle=null;
        tail=node;
        treeToDll(left);
        treeToDll(mid);
        treeToDll(right);
    }
}