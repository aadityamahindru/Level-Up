//https://www.geeksforgeeks.org/clone-binary-tree-random-pointers/

class CloneRandomTree{
    public static void main(String[] args) {
        Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		root.random = root.right.left.random;
		root.left.left.random = root.right;
		root.left.right.random = root;
		root.right.left.random = root.left.left;
		root.random = root.left;
        cloneTree(root);
    }
    static class Node
    {
        int data;
        Node left, right;
        Node random;
    
        // Constructor
        Node(int data) {
            this.data = data;
        }
    }
    public static void cloneTree(Node root){
        if(root ==null ) return ;
        Node croot= copyLeftRight(root);
        copyRandom(root,croot);
        recoverTree(root,croot);
        preorder(croot);
    }
    public static void recoverTree(Node root,Node croot){
        if(root==null) return;
        if(croot.left!=null){
            root.left=root.left.left;
            croot.left=croot.left.left;
        } else{
            root.left=null;
        }
        recoverTree(root.left,croot.left);
        recoverTree(root.right,croot.right);
    }
    public static void copyRandom(Node root,Node croot){
        if(root==null) return;
        if(root.random!=null)
            croot.random=root.random.left;
        else
            croot.random=null;
        if(root.left!=null&&croot.left!=null)
        copyRandom(root.left.left,croot.left.left);
        copyRandom(root.right,croot.right);
    }
    public static Node copyLeftRight(Node node){
        if(node ==null) return null;
        Node left=node.left;
        Node nn=new Node(node.data);
        node.left=nn;
        nn.left=left;
        if(left!=null){
            left.left=copyLeftRight(left);
        }
        nn.right=copyLeftRight(node.right);
        return nn;
    }
    public static void preorder(Node root)
	{
		if (root == null) {
			return;
		}
		System.out.print(root.data + " -> (");
		System.out.print((root.left != null ? root.left.data : "X") + ", ");
		System.out.print((root.right != null ? root.right.data : "X") + ", ");
		System.out.println((root.random != null ? root.random.data : "X") + ")");
		preorder(root.left);
		preorder(root.right);
	}

}