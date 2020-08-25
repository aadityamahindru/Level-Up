//leetcode 236

class Solution {
    TreeNode lca=null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findLca(root,p,q);
        return lca;
    }
    public boolean findLca(TreeNode node, TreeNode p, TreeNode q){
        if(node==null){
            return false;
        }
        boolean selfDone=false;
        if(node.val==p.val||node.val==q.val){
            selfDone=true;
        }
        boolean leftDone=findLca(node.left,p,q);
        if(lca!=null){
            return true;
        }
        boolean rightDone=findLca(node.right,p,q);
        if((rightDone&&leftDone)||(rightDone&&selfDone)||(selfDone&&leftDone)){
            lca=node;
        }
        return selfDone||leftDone||rightDone;
    }
}