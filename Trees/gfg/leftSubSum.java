import java.util.*;
class leftSubSum{
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
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }
    static int idx = 0;
    public static Node constructTree(int[] arr){
        if(idx==arr.length || arr[idx]==-1){
            idx++;
            return null;
        }
        Node node=new Node(arr[idx++]);

        node.left = constructTree(arr);
        node.right = constructTree(arr);

        return node;
    }
   public static void main(String[] args) {
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        solve(root);
   }
   public static void solve(Node node){
        Node find=findNext(node,700);
        if(find==null) System.out.println("null");
        else
        System.out.println(find.data);
   }
   public static int leftSum(Node node){
       if(node==null) return 0;
       int lc=leftSum(node.left);
       int rc=leftSum(node.right);
       node.data+=lc;
       return node.data+rc;
   }


   //https://www.geeksforgeeks.org/double-tree/
   public static void doubleNN(Node node){
       if(node==null){
           return;
       }
       Node nn=new Node(node.data);
       nn.left=node.left;
       node.left=nn;
       doubleNN(nn.left);
       doubleNN(node.right);
   }

    //https://www.geeksforgeeks.org/find-next-right-node-of-a-given-key/
    public static Node findNext(Node node,int val){
        Queue<Node> q=new ArrayDeque<>();
        q.add(node);
        while(q.size()>0){
            Node temp=q.remove();
            if(temp.data==val){
                if(q.size()>0) return q.peek();
                else return null;
            }
            if(temp.left!=null) q.add(temp.left);
            if(temp.right!=null) q.add(temp.right);
        }
        return null;
    }
}