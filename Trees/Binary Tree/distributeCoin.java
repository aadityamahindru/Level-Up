
// leetcode 979

class Solution {
    int totalMoves=0;
    public int distributeCoins(TreeNode root) {
        if(distributeCoins_(root)!=0) return -1;
        return totalMoves;
    }
    public int distributeCoins_(TreeNode node){
        if(node==null) return 0;
        int lans=distributeCoins_(node.left);
        int rans=distributeCoins_(node.right);
        totalMoves+=Math.abs(lans)+Math.abs(rans);
        return node.val-1+lans+rans;
    }
}