// leetcode 968

class Solution {
    int cameras=0;
    public int minCameraCover(TreeNode root) {
        if(minCameraCover_(root)==-1) cameras++;
        return cameras;
    }
    public int minCameraCover_(TreeNode root){
        if(root==null) return 1;
        int lans=minCameraCover_(root.left);
        int rans=minCameraCover_(root.right);
        if(lans==-1||rans==-1){
            cameras++;
            return 0;
        }
        if(lans==0||rans==0) return 1;
        return -1;
    }
}