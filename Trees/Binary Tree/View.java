import java.util.*;
class View{
    public static void main(String[] args) {
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
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
        //zigzag(node);
        // leftView(node);
        //rightView(node);
       // verticalView(node);
        //verticalSum(node);
        // bottomRightPView(node);
        //bottomLeftPView(node);
        //topView(node);
        // topViewLvl(node);
       // diagonalLeft(node);
        diagonalRight(node);
    }
    public static void zigzag(Node node){
        Stack<Node> ms=new Stack<>();
        Stack<Node> cs=new Stack<>();
        int lvl=1;
        ms.push(node); 
        while(ms.size()>0){
            Node temp=ms.pop();
            System.out.print(temp.data+" ");
            if(lvl%2!=0){
                if(temp.left!=null)  cs.push(temp.left);
                if(temp.right!=null) cs.push(temp.right);
            }else{
                if(temp.right!=null) cs.push(temp.right);
                if(temp.left!=null)  cs.push(temp.left);
            }
            if(ms.size()==0){
                System.out.println();
                ms=cs;
                cs=new Stack<>();
                lvl++;
            }
        }
    }

    // left view
    public static void leftView(Node node){
        LinkedList<Node> q=new LinkedList<>();
        q.addLast(node);
        while(q.size()>0){
            int size=q.size();
            System.out.print(q.getFirst().data+" ");
            while(size-->0){
                Node temp=q.removeFirst();
                if(temp.left!=null)  q.addLast(temp.left);
                if(temp.right!=null) q.addLast(temp.right);
            }
        }
    }

    // right view
    public static void rightView(Node node){
        LinkedList<Node> q=new LinkedList<>();
        q.addLast(node);
        while(q.size()>0){
            int size=q.size();
            Node prev=null;
            while(size-->0){
                Node temp=q.removeFirst();
                prev=temp;
                if(temp.left!=null)  q.addLast(temp.left);
                if(temp.right!=null) q.addLast(temp.right);
            }
            System.out.print(prev.data+" ");
        }
    }
    // vertical order 
    static class vPair{
        Node node;
        int level;
        vPair(Node node,int level){
            this.node=node;
            this.level=level;
        }
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
    public static void verticalView(Node node){
        int maxMin[]=new int[2];
        width_(node,0,maxMin);
        ArrayList<Integer>[] ans=new ArrayList[maxMin[1]-maxMin[0]+1];
        for(int i=0;i<ans.length;i++)  ans[i]=new ArrayList<>();
        LinkedList<vPair> q=new LinkedList<>();
        q.addLast(new vPair(node,Math.abs(maxMin[0])));
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                vPair temp=q.removeFirst();
                int level=temp.level;
                ans[level].add(temp.node.data);
                if(temp.node.left!=null)  q.addLast(new vPair(temp.node.left,level-1));
                if(temp.node.right!=null) q.addLast(new vPair(temp.node.right,level+1));
            }
        }
        for(ArrayList<Integer> list:ans){
            for(Integer val:list){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }

    // vertical sum================
    public static void verticalSum(Node node){
        int maxMin[]=new int[2];
        width_(node,0,maxMin);
        int[] ans=new int[maxMin[1]-maxMin[0]+1];
        LinkedList<vPair> q=new LinkedList<>();
        q.addLast(new vPair(node,Math.abs(maxMin[0])));
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                vPair temp=q.removeFirst();
                int level=temp.level;
                ans[level]+=temp.node.data;
                if(temp.node.left!=null)  q.addLast(new vPair(temp.node.left,level-1));
                if(temp.node.right!=null) q.addLast(new vPair(temp.node.right,level+1));
            }
        }
        for(int val:ans){
            System.out.print(val+" ");
        }
    }
    // bottom right prefer=========
    public static void bottomRightPView(Node node){
        int maxMin[]=new int[2];
        width_(node,0,maxMin);
        int[] ans=new int[maxMin[1]-maxMin[0]+1];
        LinkedList<vPair> q=new LinkedList<>();
        q.addLast(new vPair(node,Math.abs(maxMin[0])));
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                vPair temp=q.removeFirst();
                int level=temp.level;
                ans[level]=temp.node.data;
                if(temp.node.left!=null)  q.addLast(new vPair(temp.node.left,level-1));
                if(temp.node.right!=null) q.addLast(new vPair(temp.node.right,level+1));
            }
        }
        for(int val:ans){
            System.out.print(val+" ");
        }
    }
    // bootom left prefer
    static class bPair{
        Node node;
        int level;
        int height;
        bPair(Node node,int level,int height){
            this.node=node;
            this.level=level;
            this.height=height;
        }
    }
    public static void bottomLeftPView(Node node){
        int maxMin[]=new int[2];
        width_(node,0,maxMin);
        bPair[] ans=new bPair[maxMin[1]-maxMin[0]+1];
        LinkedList<bPair> q=new LinkedList<>();
        q.addLast(new bPair(node,Math.abs(maxMin[0]),0));
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                bPair temp=q.removeFirst();
                int level=temp.level;
                int height=temp.height;
                if(ans[level]==null){
                    ans[level]=temp;
                }else if(height>ans[level].height){
                    ans[level]=temp;
                }
                if(temp.node.left!=null)  q.addLast(new bPair(temp.node.left,level-1,height+1));
                if(temp.node.right!=null) q.addLast(new bPair(temp.node.right,level+1,height+1));
            }
        }
        for(bPair pair:ans){
            System.out.print(pair.node.data+" ");
        }
    }

    // top view
    public static void topView(Node node){
        int maxMin[]=new int[2];
        width_(node,0,maxMin);
        Node ans[]=new Node[maxMin[1]-maxMin[0]+1];
        LinkedList<vPair> q=new LinkedList<>();
        q.addLast(new vPair(node,Math.abs(maxMin[0])));
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                vPair temp=q.removeFirst();
                int level=temp.level;
                if(ans[level]==null)
                ans[level]=temp.node;
                if(temp.node.left!=null)  q.addLast(new vPair(temp.node.left,level-1));
                if(temp.node.right!=null) q.addLast(new vPair(temp.node.right,level+1));
            }
        }
        for(Node ele:ans){
            System.out.print(ele.data+" ");
        }
    }

    // level-wise top
    public static void topViewLvl(Node node){
        int maxMin[]=new int[2];
        width_(node,0,maxMin);
        Node ans[]=new Node[maxMin[1]-maxMin[0]+1];
        LinkedList<vPair> q=new LinkedList<>();
        q.addLast(new vPair(node,Math.abs(maxMin[0])));
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                vPair temp=q.removeFirst();
                int level=temp.level;
                if(ans[level]==null)
                ans[level]=temp.node;
                if(temp.node.left!=null)  q.addLast(new vPair(temp.node.left,level-1));
                if(temp.node.right!=null) q.addLast(new vPair(temp.node.right,level+1));
            }
        }
        int idx=Math.abs(maxMin[0]);
        System.out.println(ans[idx].data);
        for(int i=1;i<=maxMin[1];i++){
            if(idx-i>=0){
                System.out.print(ans[idx-i].data+" ");
            }
            if(idx+i<ans.length){
                System.out.print(ans[idx+i].data+" ");
            }
            System.out.println();
        }
    }

    //diagonal left 
    public static void diagonalLeft(Node node){
        int maxMin[]=new int[2];
        width_(node,0,maxMin);
        ArrayList<Integer>[] ans=new ArrayList[0-maxMin[0]+1];
        for(int i=0;i<ans.length;i++)  ans[i]=new ArrayList<>();
        LinkedList<vPair> q=new LinkedList<>();
        q.addLast(new vPair(node,Math.abs(maxMin[0])));
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                vPair temp=q.removeFirst();
                int level=temp.level;
                ans[level].add(temp.node.data);
                if(temp.node.left!=null)  q.addLast(new vPair(temp.node.left,level-1));
                if(temp.node.right!=null) q.addLast(new vPair(temp.node.right,level));
            }
        }
        for(ArrayList<Integer> list:ans){
            for(Integer val:list){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }

    // diagonal right==============
    public static void diagonalRight(Node node){
        int maxMin[]=new int[2];
        width_(node,0,maxMin);
        ArrayList<Integer>[] ans=new ArrayList[maxMin[1]+1];
        for(int i=0;i<ans.length;i++)  ans[i]=new ArrayList<>();
        LinkedList<vPair> q=new LinkedList<>();
        q.addLast(new vPair(node,0));
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                vPair temp=q.removeFirst();
                int level=temp.level;
                ans[level].add(temp.node.data);
                if(temp.node.left!=null)  q.addLast(new vPair(temp.node.left,level));
                if(temp.node.right!=null) q.addLast(new vPair(temp.node.right,level+1));
            }
        }
        for(ArrayList<Integer> list:ans){
            for(Integer val:list){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
}