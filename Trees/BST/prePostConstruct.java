import java.util.*;
class prePostConstruct{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }
    public static void main(String[] args) {
        solve();
    }
    public static void display(Node node){
        if(node==null) return;
        StringBuilder sb = new StringBuilder();
        
        sb.append(node.left!=null?node.left.data+"" : ".");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.right!=null?node.right.data+"" : ".");

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }
    static int idx;
    public static void solve(){
        //int arr[]={7,3,1,0,2,6,4,5,12,9,8,11,10,13,15,14};
        //int arr[]={0,2,1,5,4,6,3,8,10,11,9,14,15,13,12,7};
        int arr[]={50,17,72,12,23,54,76,9,14,19,67};
        display(inOrderConstruct(arr));
        // idx=arr.length-1;
        // display(postOrderConstruct(arr,-(int)1e8,(int)1e8));
    }
    public static Node preOrderConstruct(int arr[],int lRange,int rRange){
        if(idx>=arr.length||arr[idx]<lRange||arr[idx]>rRange){
            return null;
        }
        Node node=new Node(arr[idx++]);
        node.left=preOrderConstruct(arr,lRange,node.data);
        node.right=preOrderConstruct(arr,node.data,rRange);
        return node;
    }
    public static Node postOrderConstruct(int arr[],int lRange,int rRange){
        if(idx<0||arr[idx]<lRange||arr[idx]>rRange){
            return null;
        }
        Node node=new Node(arr[idx--]);
        node.right=postOrderConstruct(arr,node.data,rRange);
        node.left=postOrderConstruct(arr,lRange,node.data);
        return node;
    }
    static class Pair{
        Node parent=null;
        int lRange=(int)-1e8;
        int rRange=(int)1e8;
        Pair(Node parent,int lRange,int rRange){
            this.parent=parent;
            this.lRange=lRange;
            this.rRange=rRange;
        }
    }
    // int arr[]={50,17,72,12,23,54,76,9,14,19,67};
    // public static Node inOrderConstruct(int arr[]){
    //     Node root=new Node(arr[0]);
    //     LinkedList<Pair> q=new LinkedList<>();
    //     q.addLast(new Pair(root,(int)-1e8,arr[0]));
    //     q.addLast(new Pair(root,arr[0],(int)1e8));
    //     int idx=1;
    //     while(idx<arr.length){
    //         Pair temp=q.removeFirst();
    //         if(arr[idx]>temp.lRange&&arr[idx]<temp.rRange){
    //             Node node=new Node(arr[idx]);
    //             if(arr[idx]<temp.parent.data) temp.parent.left=node;
    //             else temp.parent.right=node;
    //             idx++;  
    //             q.addLast(new Pair(node,temp.lRange,node.data));
    //             q.addLast(new Pair(node,node.data,temp.rRange));
    //         }
    //     }
    //     return root;
    // }
}