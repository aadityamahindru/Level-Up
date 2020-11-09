class unionFind{
    public static void main(String[] args) {
        int arr[][]={{1,0},{0,2},{2,1},{0,3},{3,4}};
        isTree(arr);
    }
    static int parr[];
    public static int find(int u){
        if(parr[u]==u) return u;
        return parr[u]=find(parr[u]);
    }
    public static void isTree(int arr[][]){
        int n=arr.length;
        parr=new int[n+1];
        int count=n+1;
        for(int i=0;i<=n;i++) parr[i]=i;
        for(int a[]:arr){
            int p1=find(a[0]);
            int p2=find(a[1]);
            if(p1!=p2){
                parr[p1]=p2;
                count--;
            }else{
                System.out.println("Not a Tree");
                return;
            }
        }
        if(count==1)System.out.println("It is a Tree");
        else System.out.println("Not a Tree");
    }
}