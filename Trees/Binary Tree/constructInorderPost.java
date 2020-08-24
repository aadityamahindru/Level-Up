//leetcode 106
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
         return buildTree_(postorder,0,postorder.length-1,inorder,0,inorder.length-1);
    }
    public TreeNode buildTree_(int postorder[],int psi,int pei,int inorder[],int isi,int iei){
        if(psi>pei){
            return null;
        }
        int idx=isi;
        while(postorder[pei]!=inorder[idx]) idx++;
        int len=idx-isi;
        TreeNode node=new TreeNode(postorder[pei]);
        node.left=buildTree_(postorder,psi,psi+len-1,inorder,isi,idx-1);
        node.right=buildTree_(postorder,psi+len,pei-1,inorder,idx+1,iei);
        return node;
    }
}