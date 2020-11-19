import java.util.*;
class findMax{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
        }
    }
    public static Node constructBST(ArrayList<Integer> arr,int si,int ei){
        if(si>ei) return null; 

        int mid = (si + ei ) >> 1;
        Node node = new Node(arr.get(mid));
        node.left = constructBST(arr,si,mid - 1);
        node.right = constructBST(arr,mid + 1, ei);
        return node;
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
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        int a[]={1,2,3,5,9,12,19,21,25};
        for(int i = 0; i <a.length; i++) arr.add(a[i]);
        Node root = constructBST(arr,0,arr.size()-1);
        display(root);
        System.out.println(findMaxElement(root,1,25));

    }
    static int maxNode=0;
    public static int findMaxElement(Node node, int val1,int val2){
        Node lca=findLca(node,val1,val2);
        lcaToNodePath(lca,val1);
        lcaToNodePath(lca,val2);
        return maxNode;
    }
    public static Node findLca(Node node,int val1,int val2){
        if(node==null) return null;
        if(val1<node.data&&val2<node.data) return findLca(node.left,val1,val2);
        else if(val1>node.data&&val2>node.data) return findLca(node.right,val1,val2);
        else return node;
    }
    public static void lcaToNodePath(Node node,int val){
        if(node==null||node.data==val) return;
        maxNode=Math.max(maxNode,node.data);
        if(val<node.data) lcaToNodePath(node.left,val);
        else lcaToNodePath(node.right,val);
    }
}