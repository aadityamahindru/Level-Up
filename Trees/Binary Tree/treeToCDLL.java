class Node{
    int data;
    Node left,right;
    Node(int d){
        data=d;
        left=right=null;
    }
}
class Tree
{ 
    Node head=null;
    Node prev=null;
    Node bTreeToClist(Node root)
    {
        //your code here
        bTreeToClist_(root);
        prev.right=head;
        head.left=prev;
        return head;
    }
    public void bTreeToClist_(Node node){
        if(node==null){
            return;
        }
        bTreeToClist_(node.left);
        if(head==null)
        head=node;
        else{
            node.left=prev;
            prev.right=node;
        }
        prev=node;
        bTreeToClist_(node.right);
    }
    
}
    
