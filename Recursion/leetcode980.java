class Solution {
    public int uniquePathsIII(int[][] grid) {
        if(grid.length==0||grid[0].length==0)
            return 0;
        int count=0,sr=0,sc=0,er=0,ec=0;
        int dir[][]={{0,-1},{-1,0},{0,1},{1,0}};
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]>=0)
                    count++;
                if(grid[i][j]==1){
                    sr=i;
                    sc=j;
                }else if(grid[i][j]==2){
                    er=i;
                    ec=j;
                }
            }
        } 
        return path(sr,sc,er,ec,grid,count-1,dir);
    }
    public int path(int sr,int sc,int er,int ec,int arr[][],int count,int dir[][]){
        if(sr==er&&sc==ec){
            return count==0?1:0;
        }
        int temp=arr[sr][sc];
        int ct=0;
        arr[sr][sc]=-1;
        for(int i=0;i<dir.length;i++){
            int r=sr+dir[i][0];
            int c=sc+dir[i][1];
            if(r>=0&&r<arr.length&&c>=0&&c<arr[0].length&&arr[r][c]>=0)
            ct+=path(r,c,er,ec,arr,count-1,dir);
        }
        arr[sr][sc]=temp;
        return ct;
    }
}