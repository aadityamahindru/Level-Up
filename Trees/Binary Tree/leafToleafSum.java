//https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/


class Tree
{
    public int LeafToLeafAns= (int)-1e8;
    public int leafToleaf(Node node){
        if(node==null){
            return (int)-1e8;
        }
        if(node.left==null&&node.right==null){
            return node.data;
        }
        int lMax=leafToleaf(node.left);
        int rMax=leafToleaf(node.right);
        if(node.left!=null&&node.right!=null){
            LeafToLeafAns=Math.max(LeafToLeafAns,lMax+rMax+node.data);
        }
        return Math.max(lMax,rMax)+node.data;
    }
    int maxPathSum(Node root)
    { 
       leafToleaf(root);
       return LeafToLeafAns;
    } 
}
//using pair


class Pair{
    int LeafToLeafAns;
    int max;
    Pair(int LeafToLeafAns,int max){
        this.LeafToLeafAns=LeafToLeafAns;
        this.max=max;
    }
    Pair(){
        LeafToLeafAns=(int)-1e8;
        max=(int)-1e8;
    }
}
class Tree
{
    public Pair leafToleaf(Node node){
    if(node==null){
        int LToLAns=(int)-1e8;
        int mx=(int)-1e8;
        return new Pair(LToLAns,mx);
    }
    if(node.left==null&&node.right==null){
        int LToLAns=(int)-1e8;
        return new Pair(LToLAns,node.data);
    }
    Pair lMax=leafToleaf(node.left);
    Pair rMax=leafToleaf(node.right);
    Pair res=new Pair();
    res.LeafToLeafAns=Math.max(lMax.LeafToLeafAns,Math.max(rMax.LeafToLeafAns,lMax.max+rMax.max+node.data));
    res.max=Math.max(lMax.max,rMax.max)+node.data;
    return res;
}
    int maxPathSum(Node root)
    { 
       Pair ans=leafToleaf(root);
     return ans.LeafToLeafAns;
    } 
}