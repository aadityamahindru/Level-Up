import java.util.*;
class burningForest{
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
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        burn(node,30,ans);
        for(ArrayList<Integer> list:ans){
            for(Integer val:list){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
    public static void burinngNodes(Node node,int time,ArrayList<ArrayList<Integer>> ans){
        if(node==null){
            return;
        }
        if(time==ans.size()){
            ans.add(new ArrayList<Integer>());
        }
        ans.get(time).add(node.data);
        burinngNodes(node.left,time+1,ans);
        burinngNodes(node.right,time+1,ans);
    }
    public static int burn(Node node,int fFire,ArrayList<ArrayList<Integer>> ans){
        if(node ==null){
            return -1;
        }
        if(node.data==fFire){
            burinngNodes(node,0,ans);
            return 1;
        }
        int ld=burn(node.left,fFire,ans);
        if(ld!=-1){
            if(ld==ans.size())
            ans.add(new ArrayList<Integer>());
            ans.get(ld).add(node.data);
            burinngNodes(node.right,ld+1,ans);
            return ld+1;
        }
        int rd=burn(node.right,fFire,ans);
        if(rd!=-1){
            if(rd==ans.size())
            ans.add(new ArrayList<Integer>());
            ans.get(rd).add(node.data);
            burinngNodes(node.left,rd+1,ans);
            return rd+1;
        }
        return -1;
    }
}