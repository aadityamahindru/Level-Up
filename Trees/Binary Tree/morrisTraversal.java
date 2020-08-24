class morrisTraversal{
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
    public static void main(String[] args) {
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        solve(root);
    }
    public static void solve(Node node){
        morrisInorder(node);
        System.out.println();
        morrisPreorder(node);
    }
    public static Node rightMost(Node node,Node curr){
        while(node.right!=null&&node.right!=curr) node=node.right;
        return node;
    }
    public static void morrisInorder(Node node){
        Node curr=node;
        while(curr!=null){
            Node next=curr.left;
            if(next==null){
                System.out.print(curr.data+" ");
                curr=curr.right;
            }else{
                Node rightMost=rightMost(next,curr);
                if(rightMost.right==null){
                    rightMost.right=curr;
                    curr=curr.left;
                }else{
                    System.out.print(curr.data+" ");
                    rightMost.right=null;
                    curr=curr.right;
                }
            }
        }
    }
    public static void morrisPreorder(Node node){
        Node curr=node;
        while(curr!=null){
            Node next=curr.left;
            if(next==null){
                System.out.print(curr.data+" ");
                curr=curr.right;
            }else{
                Node rightMost=rightMost(next,curr);
                if(rightMost.right==null){
                    System.out.print(curr.data+" ");
                    rightMost.right=curr;
                    curr=curr.left;
                }else{
                    rightMost.right=null;
                    curr=curr.right;
                }
            }
        }
    }
}