import java.util.*;
class GFG{
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
        int[] arr={26,18,10,8,3,-1,-1,4,-1,-1,11,6,-1,-1,-1,21,-1,-1,30,7,-1,-1,5,12,-1,-1,2,-1,-1};
        Node root = constructTree(arr);
        solve(root);
   }
   static class Pair{
       int ls;
       int rs;
       Pair(int ls,int rs){
           this.ls=ls;
           this.rs=rs;
       }
   }
   public static void solve(Node node){
        // HashMap<Integer,Integer> hm =new HashMap<>();
        // reversePath(node,100,hm,1);
        // display(node);
        ArrayList<Integer> ans=new ArrayList<>();
        leftRifgtSum(node,ans);
        System.out.println(ans);
   }
   public static Pair leftRifgtSum(Node node ,ArrayList<Integer> ans){
       if(node==null){
           return new Pair(0,0);
       }
       Pair left=leftRifgtSum(node.left,ans);
       Pair right=leftRifgtSum(node.right,ans);
       if(left.ls!=0&&right.rs!=0&&(left.ls==right.rs)){
           ans.add(node.data);
       }
       return new Pair(left.ls+node.data,right.rs+node.data);
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

    //https://www.geeksforgeeks.org/deepest-left-leaf-node-in-a-binary-tree/
    static Node deepestLeaf=null;
    static int maxdepth=0;
    public static void deepestLeftLeaf(Node node,int lvl,boolean isLeft){
        if(node==null) return;
        if(isLeft&&node.right==null&&node.left==null&&lvl>maxdepth){
            maxdepth=lvl;
            deepestLeaf=node;
            return;
        }
        deepestLeftLeaf(node.left,lvl+1,true);
        deepestLeftLeaf(node.right,lvl+1,false);
    }

    // https://www.geeksforgeeks.org/remove-nodes-root-leaf-paths-length-k/

    public static Node removeK(Node node,int k,int lvl){
        if(node==null) return null;
        if(node.left==null&&node.right==null){
            if(lvl!=k) return null;
            return node;
        }
        node.left=removeK(node.left,k,lvl+1);
        node.right=removeK(node.right,k,lvl+1);
        if(node.left==null&&node.right==null) return null;
        return node;
    }
    //https://www.geeksforgeeks.org/reverse-tree-path/
    static int nidx=1;
    public static boolean reversePath(Node node,int val,HashMap<Integer,Integer> hm,int lvl){
        if(node==null)  return false;
        hm.put(lvl,node.data);
        if(node.data==val){
            node.data=hm.get(nidx++);
            return true;
        }
        boolean right=false;
        boolean left=reversePath(node.left,val,hm,lvl+1);
        if(!left){
            right= reversePath(node.right,val,hm,lvl+1);
        }
        if(!left&& !right) hm.remove(lvl);
        else if(left||right){
            node.data=hm.get(nidx++);
            return true;
        }
        return false;
    } 
}