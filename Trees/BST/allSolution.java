import java.util.*;
class allSolution{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
        }
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
    public static Node constructBST(ArrayList<Integer> arr,int si,int ei){
        if(si>ei) return null; 

        int mid = (si + ei ) >> 1;
        Node node = new Node(arr.get(mid));
        node.left = constructBST(arr,si,mid - 1);
        node.right = constructBST(arr,mid + 1, ei);
        return node;
    }
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 1; i <= 15; i++) arr.add(i*10);
        Node root = constructBST(arr,0,arr.size()-1);
        solve(root);
    }
    public static void solve(Node node){
        //solver(node,120);
        // Node root=addNode(node,160);
        // display(root);
    }
    public static void solver(Node node,int data){
        Node pred=null,succ=null,curr=node;
        while(curr!=null){
            if(curr.data==data){
                if(curr.left!=null){
                    pred=curr.left;
                    while(pred.right!=null) pred=pred.right;
                }
                if(curr.right!=null){
                    succ=curr.right;
                    while(succ.left!=null) succ=succ.left;
                }
                break;
            }else if(data<curr.data){
                succ=curr;
                curr=curr.left;
            }else{
                pred=curr;
                curr=curr.right;
            }
        }
        System.out.println(pred.data+" "+succ.data);
    }
    public static Node addNode(Node node,int data){
        if(node==null){
            return new Node(data);
        }
        if(data<node.data) node.left=addNode(node.left,data);
        if(data>node.data) node.right=addNode(node.right,data);
        return node;
    }
    public static Node removeNode(Node node,int data){
        if(node==null){
            return null;
        }
        if(data<node.val){
          node.left=removeNode(node.left,data);  
        }else if(data>node.val){
            node.right=removeNode(node.right,data);
        }else{
            if(node.right==null||node.left==null)
                return node.left!=null?node.left:node.right;
            int minEle=minimum(node.right);
            node.val=minEle;
            node.right=removeNode(node.right,minEle);
        }
        return node;  
    }
    public int minimum(Node node){
        if(node.left==null){
            return node.val;
        }
        return minimum(node.left);
    }
}