import java.util.*;
class kFar{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
        }
    }
    static int idx=0;
    public static Node construct(int arr[]){
        Queue<Node> q=new ArrayDeque<>();
        Node root=new Node(arr[0]);
        q.add(root);
        int idx=1;
        while(q.size()>0){
            Node temp=q.remove();
            Node left=(arr[idx]==-1)?null: new Node(arr[idx]);
            idx++;
            Node right=(arr[idx]==-1)?null: new Node(arr[idx]);
            idx++;
            temp.left=left;
            temp.right=right;
            if(left!=null) q.add(left);
            if(right!=null) q.add(right);
        }
        return root;
    }
    public static void main(String[] args) {
        int arr[]={5,6,10,2,3,-1,-1,-1,-1,-1,9,-1,-1};
        int data=3;
        int k=1;
        Node root =construct(arr);
        findKfar(root,data,k);
    }
    public static void findKfar(Node node,int data,int k){
        ArrayList<Node> path=nodeToRootPath(node,data);
        Node block=null;
        for(int i=0;i<path.size()&&i<=k;i++){
            Node nNode=path.get(i);
            if(i!=0) block=path.get(i-1);
            printKfar(nNode,k-i,block);
        }
    }
    public static void printKfar(Node node,int k,Node block){
        if(node==null||node==block) return;
        if(k==0){
            System.out.println(node.data);
            return;
        }
        printKfar(node.left,k-1,block);
        printKfar(node.right,k-1,block);
    }
    public static ArrayList<Node> nodeToRootPath(Node node,int data){
        if(node == null) return new ArrayList<Node>();
        if(node.data==data){
            ArrayList<Node> bs=new ArrayList<>();
            bs.add(node);
            return bs;
        }
        ArrayList<Node> left= nodeToRootPath(node.left,data);
        if(left.size()>0){
            left.add(node);
            return left;
        }
        ArrayList<Node> right= nodeToRootPath(node.right,data);
        if(right.size()>0){
            right.add(node);
            return right;
        }
        return new ArrayList<Node>();
    }
}