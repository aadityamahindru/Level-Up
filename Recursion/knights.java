class knights{
    public static void main(String[] args) {
       System.out.println(knightsPath(5));
    }
    public static int knightsPath(int n){
        int arr[][]=new int[n][n];
        int dir[][]={{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
        return paths(0,0,1,arr,dir);
    }
    public static int paths(int x,int y,int count,int arr[][],int dir[][]){
        if(count==arr.length*arr.length){
            arr[x][y]=count;
            display(arr);
            arr[x][y]=0;
            return 1;
        }
        int ct=0;
        arr[x][y]=count;
        for(int i=0;i<dir.length;i++){
            int r=x+dir[i][0];
            int c=y+dir[i][1];
            if(r>=0&&r<arr.length&&c>=0&&c<arr.length&&arr[r][c]==0){
                ct+=paths(r,c,count+1,arr,dir);
            }
        }
        arr[x][y]=0;
        return ct;
    }
    public static void display(int arr[][]){
        int n=arr.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}