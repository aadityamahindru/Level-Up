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
    public static void main(String[] args) {
        
    }
}