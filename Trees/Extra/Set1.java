class Set1{
    //337 leetcode
    class Pair{
        int rob=0;
        int nrob=0;
        Pair(int rob,int nrob){
            this.rob=rob;
            this.nrob=nrob;
        }
    }
    public int rob(TreeNode root) {
        if(root==null) return 0;
        Pair ans=rob_(root);
        return Math.max(ans.rob,ans.nrob);
    }
    public Pair rob_(TreeNode node){
        if(node ==null) return new Pair(0,0);
        Pair left=rob_(node.left);
        Pair right=rob_(node.right);
        Pair myans=new Pair(0,0);
        myans.rob=node.val+left.nrob+right.nrob;
        myans.nrob=Math.max(left.rob,left.nrob)+Math.max(right.rob,right.nrob);
        return myans;
    }
    //1339 leetcode
    long max=0;
    int mod=(int)1e9+7;
    public int maxProduct(TreeNode root) {
        int sum=totalSum(root);
        maxProduct(root,sum);
        return (int)(max%mod);
    }
    public int totalSum(TreeNode node){
        if(node==null) return 0;
        int left=totalSum(node.left);
        int right=totalSum(node .right);
        return left+right+node.val;
    }
    public long maxProduct(TreeNode node,int sum){
        if(node==null) return 0;
        long left=maxProduct(node.left,sum);
        long right=maxProduct(node.right,sum);
        long mySum=left+right+node.val;
        max=Math.max(max,(mySum)*(sum-mySum));
        return mySum;
    }
    //1325 leetcode
    public TreeNode removeLeafNodes(TreeNode node, int target) {
        if(node==null) return null;
        node.left=removeLeafNodes(node.left,target);
        node.right=removeLeafNodes(node.right,target);
        if(node.left==null&&node.right==null&&node.val==target){
            return null;
        }
        return node;
    }
    
    //116 leetcode
    //bfs
    public Node connect(Node root) {
        if(root==null) return null;
        ArrayDeque<Node> q=new ArrayDeque<>();
        q.add(root);
        while(q.size()>0){
            int size=q.size();
            Node prev=null;
            while(size-->0){
                Node temp=q.remove();
                if(prev!=null) prev.next=temp;
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                prev=temp;
            }
            prev.next=null;
        }
        return root;
    }
    //recc
    public Node connect(Node node) {
        if(node==null) return node;
          if(node.left!=null) node.left.next=node.right;
          if(node.right!=null && node.next!=null) node.right.next=node.next.left;
          node.left=connect(node.left);
          node.right=connect(node.right);
          return node;
      }

      //o(1) iterative
      public Node connect(Node node) {
        if(node==null) return node;
          Node nn=node;
          while(node.left!=null){
              Node curr=node;
              while(curr!=null){
                  curr.left.next=curr.right;
                  if(curr.next!=null) curr.right.next=curr.next.left;
                  curr=curr.next;
              }
              node=node.left;
          }
          return nn;
      }
    public static void main(String[] args) {
        
    }
}