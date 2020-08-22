class allSolution{
    static class Pair{
        int height=0;
        int size=0;
        boolean find=false;
        Node pred=null,succ=null,prev=null;
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
    public static void main(String[] args) {
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        solve(root);
    }
    public static void solve(Node node){
        Pair ans=new Pair();
        solver(node,0,60,ans);
        System.out.println(ans.height+" "+ans.size+" "+ans.find+" "+ans.pred.data+" "+ans.succ.data);
    }
    public static void solver(Node node,int level,int data,Pair ans){
        if(node==null){
            return;
        }
        ans.height=Math.max(ans.height,level);
        ans.size++;
        ans.find=ans.find||node.data==data;
        if(ans.pred==null&&node.data==data)  ans.pred=ans.prev;
        if(ans.prev!=null&&ans.prev.data==data) ans.succ=node;
        ans.prev=node;
        solver(node.left,level+1,data,ans);
        solver(node.right,level+1,data,ans);
    }
}