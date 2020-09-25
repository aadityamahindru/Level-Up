class bst{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }
    public static void display(Node node){
        if(node==null) return;
        StringBuilder sb = new StringBuilder();
        
        sb.append(node.left!=null?node.left.data+"" : ".");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.right!=null?node.right.data+"" : ".");

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }
    public static void main(String[] args) {
        solve();   
    }
    public static void solve(){
        int arr[]={10, 5, 1, 7, 40, 50};
        Node root=constructPre(arr,(int)-1e8,(int)1e8);
        display(root);
    }

    // https://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
    static int idx=0;
    public static Node constructPre(int arr[],int lRange,int rRange){
        if(idx==arr.length||arr[idx]<=lRange||arr[idx]>=rRange){
            return null;
        }
        Node node=new Node(arr[idx++]);
        node.left=constructPre(arr,lRange,node.data);
        node.right=constructPre(arr,node.data,rRange);
        return node;
    }

}