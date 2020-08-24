class AvlBasics{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        int height=0;
        int bal=0;
        Node(int data){
            this.data = data;
        }
    }
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
    public static void main(String[] args) {
        Node root=null;
        for(int i=1;i<20;i++) root= addNode(root,i*10);
        display(root);
    }
    public static Node getRotation(Node node){
        updateHeightBal(node);
        if(node.bal==2){
            if(node.left.bal==1){   //ll
                return rightRotation(node);
            }else{      //lr
                node.left=leftRotation(node.left);
                return rightRotation(node);
            }
        }else if(node.bal==-2){
            if(node.right.bal==-1){ //rr
                return leftRotation(node);
            }else{          //rl
                node.right=rightRotation(node.right);
                return leftRotation(node);
            }
        }
        return node;
    }
    public static void updateHeightBal(Node node){
        int lh=-1,rh=-1;
        if(node.left!=null) lh= node.left.height;
        if(node.right!=null) rh=node.right.height;
        node.bal=lh-rh;
        node.height=Math.max(lh,rh)+1;
    }
    public static Node leftRotation(Node A){
        Node B=A.right;
        Node Bleft=B.left;
        B.left=A;
        A.right=Bleft;
        updateHeightBal(A);
        updateHeightBal(B);
        return B;
    }
    public static Node rightRotation(Node A){
        Node B=A.left;
        Node Bright=B.right;
        B.right=A;
        A.left=Bright;
        updateHeightBal(A);
        updateHeightBal(B);
        return B;
    }
    public static Node addNode(Node node,int data){
        if(node==null){
            return new Node(data);
        }
        if(data<node.data) node.left=addNode(node.left,data);
        if(data>node.data) node.right=addNode(node.right,data);
       node=getRotation(node);
        return node;
    }
    public static Node removeNode(Node node,int data){
        if(node==null){
            return null;
        }
        if(data<node.data){
          node.left=removeNode(node.left,data);  
        }else if(data>node.data){
            node.right=removeNode(node.right,data);
        }else{
            if(node.right==null||node.left==null)
                return node.left!=null?node.left:node.right;
            int minEle=minimum(node.right);
            node.data=minEle;
            node.right=removeNode(node.right,minEle);
        }
        node=getRotation(node);
        return node;  
    }
    public static int minimum(Node node){
        if(node.left==null){
            return node.data;
        }
        return minimum(node.left);
    }
}