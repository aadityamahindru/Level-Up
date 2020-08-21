class Solution {
    TreeNode a=null,b=null,prev=null;
    public void recoverTree(TreeNode root) {
        recoverTree_(root);
        int temp=a.val;
        a.val=b.val;
        b.val=temp;
    }
    public boolean recoverTree_(TreeNode node){
        if(node==null){
            return false;
        }
        if(recoverTree_(node.left)) return true;
        if(prev!=null && prev.val>node.val){
            b=node;
            if(a==null) a=prev;
            else return true;
        }
        prev=node;
        if(recoverTree_(node.right)) return true;
        return false;
    }
}