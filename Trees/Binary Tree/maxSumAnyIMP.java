//124 leetcode important

class Solution {
    int lTlmaxAns=(int)-1e8;
    public int maxPathSum_(TreeNode root){
        if(root==null){
            return (int)-1e8;
        }
        int lMax=maxPathSum_(root.left);
        int rMax=maxPathSum_(root.right);
        int max_=Math.max(lMax,rMax)+root.val;
        lTlmaxAns=Math.max(Math.max(root.val,max_),Math.max(lTlmaxAns,lMax+rMax+root.val));
        return Math.max(max_,root.val);
    }
    public int maxPathSum(TreeNode root) {
        maxPathSum_(root);
        return lTlmaxAns;
    }
}