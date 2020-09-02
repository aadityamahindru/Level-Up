//https://practice.geeksforgeeks.org/problems/construct-binary-tree-from-parent-array/1

class GfG
{
    public static Node createTree(int arr[], int n)
    {
        HashMap<Integer,Node> hm = new HashMap<>();
        for(int i=0;i<n;i++){
            Node node=new Node(i);
            hm.put(i,node);
        }
        Node root =null;
        for(int i=0;i<n;i++){
            if(arr[i]==-1){
                root=hm.get(i);
            }else{
                Node parent=hm.get(arr[i]);
                if(parent.left!=null){
                    parent.right=hm.get(i);
                }else{
                    parent.left=hm.get(i);
                }
            }
        }
        return root;
    }
}

