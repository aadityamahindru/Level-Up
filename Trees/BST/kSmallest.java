// leetcode 230

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st=new Stack<>();
        pushNext(st,root);
        while(--k!=0){
            TreeNode rmnd=st.pop();
            pushNext(st,rmnd.right);
        }
        return st.peek().val;
    }
    public void pushNext(Stack<TreeNode> st,TreeNode node){
         while(node!=null){
            st.push(node);
            node = node.left;
        }
    }
}