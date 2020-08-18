class width{
    static int idx=0;
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String[] args) {
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        widthOfTree(root);

    }
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
    public static void widthOfTree(Node node){
        int arr[]=new int[2];
        width_(node,0,arr);
        System.out.println(arr[1]-arr[0]);
    }
    public static void width_(Node node,int lvl,int maxMin[]){
        if(node==null){
            return;
        }
        maxMin[0]=Math.min(maxMin[0],lvl);
        maxMin[1]=Math.max(maxMin[1],lvl);
        width_(node.left,lvl-1,maxMin);
        width_(node.right,lvl+1,maxMin);
    }
}