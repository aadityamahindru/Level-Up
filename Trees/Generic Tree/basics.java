import java.util.*;
class basics{
    public static void main(String[] args) {
        solve();
    }
    public static class Node{
        int data=0;
        ArrayList<Node> childs=new ArrayList<>();
        Node(int data){
            this.data=data;
        }
    }
    public static void solve(){
        int arr[]={10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1, 150, -1, -1, -1, -1};
        Node root=construct(arr);
        //display(root);
        lca(root,110,150);
        // printZigZag(root);
    }
    public static Node construct(int  arr[]){
        Stack<Node> st=new Stack<>();
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]!=-1){
                st.push(new Node(arr[i]));
            }else{
                Node node=st.pop();
                st.peek().childs.add(node);
            }
        }
        return st.peek();
    }
    public static void display(Node node){
        System.out.print(node.data+" -> ");
        for(Node child:node.childs){
            System.out.print(child.data+",");
        }
        if(node.childs.size()==0) System.out.print(".");
        System.out.println();
        for(Node child:node.childs){
            display(child);
        }
    }
    public static void lca(Node node,int p,int q){
        ArrayList<Node> path1=new ArrayList<>();
        ArrayList<Node> path2=new ArrayList<>();
        nodeToRootPath(node,p,path1);
        nodeToRootPath(node,q,path2);
        int i=path1.size()-1;
        int j=path2.size()-1;
        Node lca=null;
        while(i>=0&&j>=0){
           if(path1.get(i).data==path2.get(j).data) lca=path1.get(i);
           i--;
           j--;
        }
        System.out.println(lca.data);
    }
    public static boolean nodeToRootPath(Node node,int data,ArrayList<Node> path){
        if(node.data==data){
            path.add(node);
            return true;
        }
        boolean res=false;
        for(Node child:node.childs){
            res=res||nodeToRootPath(child,data,path);
        }
        if(res) path.add(node);
        return res;
    }
    public static void printZigZag(Node node){
        Stack<Node> ms=new Stack<>();
        Stack<Node> cs=new Stack<>();
        ms.push(node);
        int lvl=1;
        while(ms.size()>0){
            Node temp=ms.pop();
            System.out.print(temp.data+" ");
            if(lvl%2!=0){
                for(Node child:temp.childs){
                    cs.push(child);
                }
            }else{
                for(int i=temp.childs.size()-1;i>=0;i--){
                    Node child=temp.childs.get(i);
                    cs.push(child);
                }
            }
            if(ms.size()==0){
                System.out.println();
                ms=cs;
                lvl++;
                cs=new Stack<>();
            }
        }
    }
    public static Node linearize(Node node){
        if(node.childs.size()==0) return node;
        int n=node.childs.size();
        Node oTail=linearize(node.childs.get(n-1));
        for(Node child:node.childs){
            Node tail=
        }
    }
}