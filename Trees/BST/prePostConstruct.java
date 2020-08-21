class prePostConstruct{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }
    public static void main(String[] args) {
        solve();
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
    static int idx;
    public static void solve(){
        //int arr[]={7,3,1,0,2,6,4,5,12,9,8,11,10,13,15,14};
        int arr[]={0,2,1,5,4,6,3,8,10,11,9,14,15,13,12,7};
        idx=arr.length-1;
        display(postOrderConstruct(arr,-(int)1e8,(int)1e8));
    }
    public static Node preOrderConstruct(int arr[],int lRange,int rRange){
        if(idx>=arr.length||arr[idx]<lRange||arr[idx]>rRange){
            return null;
        }
        Node node=new Node(arr[idx++]);
        node.left=preOrderConstruct(arr,lRange,node.data);
        node.right=preOrderConstruct(arr,node.data,rRange);
        return node;
    }
    public static Node postOrderConstruct(int arr[],int lRange,int rRange){
        if(idx<0||arr[idx]<lRange||arr[idx]>rRange){
            return null;
        }
        Node node=new Node(arr[idx--]);
        node.right=postOrderConstruct(arr,node.data,rRange);
        node.left=postOrderConstruct(arr,lRange,node.data);
        return node;
    }
}