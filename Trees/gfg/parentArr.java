//https://www.geeksforgeeks.org/find-height-binary-tree-represented-parent-array/

class parentArr{
    public static void main(String[] args) {
        int parent[] = {-1, 0, 0, 1, 1, 3, 5};
        findHeight(parent);
    }
    public static void findHeight(int parent[]){
        int height[]=new int[parent.length];
        for(int i=0;i<height.length;i++){
            findHeight_(height,i,parent);
        }
        int max=0;
        for(int i=0;i<height.length;i++){
            if(height[i]>max) max= height[i];
        }
        System.out.println(max);
    }
    public static void findHeight_(int height[],int idx,int parent[]){
        if(height[idx]!=0) return;

        if(parent[idx]==-1){
            height[idx]=1;
            return;
        }
        if(height[parent[idx]]==0){
            findHeight_(height,parent[idx],parent);
        }
        height[idx]=height[parent[idx]]+1;
    }
}