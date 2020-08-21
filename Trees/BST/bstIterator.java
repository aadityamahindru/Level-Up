class BSTIterator {

    public BSTIterator(TreeNode root) {
        pushAllLeft(root);
    }
    Stack<TreeNode> st=new Stack<>();
    /* @return the next smallest number */
    public void pushAllLeft(TreeNode node){
        while(node!=null){
            st.push(node);
            node=node.left;
        }
    }
    public int next() {
        TreeNode rv=st.pop();
        pushAllLeft(rv.right);
        return rv.val;
    }
    
    /* @return whether we have a next smallest number */
    public boolean hasNext() {
        return st.size()!=0;
    }
}
