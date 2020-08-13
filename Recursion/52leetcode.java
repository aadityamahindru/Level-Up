class Solution {
    public int totalNQueens(int n) {
        //return nQueen(0,n,new boolean[n][n]);
    }
    public boolean isSafe(int row,int col,boolean box[][]){
        int dir[][]={{-1,-1},{-1,0},{-1,1}};
        for(int[] d:dir){
            for(int rad=1;rad<box.length;rad++){
                int r=row+rad*d[0];
                int c=col+rad*d[1];
                if(r>=0&&r<box.length&&c>=0&&c<box.length&&box[r][c])
                    return false;
            }
        }
        return true;
    }
    public int nQueen(int row,int n,boolean box[][]){
        if(n==0){
            return 1;
        }
        if(row==box.length){
            return 0;
        }
        int count=0;
        for(int c=0;c<box.length;c++){
            if(isSafe(row,c,box)){
                box[row][c]=true;
                count+=nQueen(row+1,n-1,box);
                box[row][c]=false;
            }
        }
        return count;
    }
}