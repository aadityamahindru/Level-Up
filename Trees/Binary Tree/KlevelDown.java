class KlevelDown{
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
        solve(root);

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
        KlevelDown_(node,2);
    }
    public static void KlevelDown_(Node node,int k){
        if(node==null){
            return;
        }
        if(k==-1){
            System.out.println(node.data);
            return;
        }
        KlevelDown_(node.left,k-1);
        KlevelDown_(node.right,k-1);
    }
}



// kfar leetcode 863

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> path=new ArrayList<>();
        nodeToRoot(root,target,null,path,K);
        return path;
    }
    public int nodeToRoot(TreeNode node,TreeNode target,TreeNode block,List<Integer> path,int k){
        if(node==null){
            return -1;
        }
        if(node.val==target.val){
            kFar(node,k,block,path);
            return 1;
        }
        int lc=nodeToRoot(node.left,target,block,path,k);
        if(lc!=-1){
            kFar(node,k-lc,node.left,path);
            return lc+1;
        }
        int rc=nodeToRoot(node.right,target,block,path,k);
        if(rc!=-1){
            kFar(node,k-rc,node.right,path);
            return rc+1;
        }
        return -1;
    }
    public void kFar(TreeNode node,int k,TreeNode block,List<Integer> path){
        if(node==null||k<0||node==block){
            return;
        }
        if(k==0){
            path.add(node.val);
            return;
        }
        kFar(node.left,k-1,block,path);
        kFar(node.right,k-1,block,path);
    }
}