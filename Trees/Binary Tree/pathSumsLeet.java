// 112 leetcode
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        if(root.left==null&&root.right==null){
            sum-=root.val;
            if(sum==0){
                return true;
            }
        }
        boolean left= hasPathSum(root.left,sum-root.val);
        boolean right= hasPathSum(root.right,sum-root.val);
        return left||right;
    }
}


//113 leet code
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> list =new ArrayList<>();
        paths(root,sum,list,ans);
        return ans;
    }
    public void paths(TreeNode root,int sum,List<Integer> list,List<List<Integer>> ans){
        if(root==null){
            return;
        }
        if(root.left==null&&root.right==null){
            sum-=root.val;
            if(sum==0){
                List<Integer> bs=new ArrayList<>(list);
                bs.add(root.val);
                ans.add(bs);
            }
            return;
        }
        list.add(root.val);
        paths(root.left,sum-root.val,list,ans);
        paths(root.right,sum-root.val,list,ans);
        list.remove(list.size()-1);
    }
}