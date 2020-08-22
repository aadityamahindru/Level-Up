import java.util.*;
class perfectLvl{
    public static void main(String[] args) {
        int[] arr={1,2,4,8,-1,-1,9,-1,-1,5,10,-1,-1,11,-1,-1,3,6,12,-1,-1,13,-1,-1,7,14,-1,-1,15,-1,-1};
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
    public static void solve(Node node){
        lvlOrder(node);
    }
    public static void lvlOrder(Node node){
        if(node==null){
            return;
        }
        System.out.print(node.data+" ");
        if(node.left!=null){
            System.out.print(node.left.data+" "+node.right.data+" ");
        }
        Queue<Node> q=new ArrayDeque<>();
        q.add(node.left);
        q.add(node.right);
        while(q.size()>0){
            Node first=q.remove();
            Node second=q.remove();
            if(first.left!=null){
                System.out.print(first.left.data+" "+second.right.data+" ");
                System.out.print(first.right.data+" "+second.left.data+" ");
                q.add(first.left);
                q.add(second.right);
                q.add(first.right);
                q.add(second.left);
            }
        }
    }
}