class leafLinked{
    public static void main(String[] args) {
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        solve(root);
    }
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }
    static int idx = 0;
    public static Node constructTree(int[] arr){
        if(idx==arr.length || arr[idx]==-1){
            idx++;
            return null;
        }
        Node node=new Node(arr[idx++]);

        node.left = constructTree(arr);
        node.right = constructTree(arr);

        return node;
    }
    static Node prev=null;
    static Node head=null;
    public static void solve(Node node){
       leaf(node);
       display(head);
    }
    public static void leaf(Node node){
        if(node==null) return;
        if(node.left==null&&node.right==null){
            if(prev==null) {
                prev=node;
                head=node;
            }
            else{
                prev.right=node;
                node.left=prev;
                prev=node;
            }
            return;
        }
        leaf(node.left);
        leaf(node.right);
    }
    public static void display(Node node){
        if(node==null) return;
        StringBuilder sb = new StringBuilder();
        
        sb.append(node.left!=null?node.left.data+"" : ".");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.right!=null?node.right.data+"" : ".");

        System.out.println(sb.toString());

        // display(node.left);
        display(node.right);
    }
}