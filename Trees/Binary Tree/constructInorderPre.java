//leetcode 105
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree_(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    public TreeNode buildTree_(int preorder[],int psi,int pei,int inorder[],int isi,int iei){
        if(psi>pei){
            return null;
        }
        int idx=isi;
        while(preorder[psi]!=inorder[idx]) idx++;
        int len=idx-isi;
        TreeNode node=new TreeNode(preorder[psi]);
        node.left=buildTree_(preorder,psi+1,psi+len,inorder,isi,idx-1);
        node.right=buildTree_(preorder,psi+len+1,pei,inorder,idx+1,iei);
        return node;
    }
}