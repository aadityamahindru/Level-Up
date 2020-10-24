import java.util.*;
class Set1{
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
    public static void nodeToRootPath(int target,ArrayList<Integer> path){
        while(target!=0){
            path.add(target);
            target/=2;
        }
    }
    public static boolean findNode(Node node,int target,ArrayList<Integer> path,int idx){
        if(node==null||idx<0) return false;
        if(node.data==target) return true;
        idx-=1;
        if(node.left!=null&&path.get(idx)==node.left.data) return findNode(node.left,target,path,idx);
        else return findNode(node.right,target,path,idx);
    }
    public static void findNode(Node node,int target){
        ArrayList<Integer> path=new ArrayList<>();
        nodeToRootPath(target,path);
        boolean ans=findNode(node,target,path,path.size()-1);
        System.out.println(ans);
    }
    public static void main(String[] args) {
        int arr[]={1,2,4,8,-1,-1,9,-1,-1,5,10,-1,-1,11,-1,-1,3,6,-1,-1,7,-1,-1};
        Node root=constructTree(arr);
        findNode(root,2);
    }
}