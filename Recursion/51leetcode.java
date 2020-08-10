class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans=new ArrayList<>();
        nQueen(0,n,new boolean[n][n],ans);
        return ans;
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
    public void nQueen(int row,int n,boolean box[][],List<List<String>> ans){
        if(n==0||row==box.length){
           if(n==0){
              fillAns(ans,box);
           }
            return;
        }
     
        for(int c=0;c<box.length;c++){
            if(isSafe(row,c,box)){
                box[row][c]=true;
                nQueen(row+1,n-1,box,ans);
                box[row][c]=false;
            }
        }
    }
    public void fillAns(List<List<String>> ans,boolean box[][]){
        List<String> ls=new ArrayList<>();
        for(int i=0;i<box.length;i++){
            String str="";
            for(int j=0;j<box.length;j++){
                if(box[i][j]){
                    str+="Q";
                }else{
                    str+=".";
                }
            }
            ls.add(str);
        }
        ans.add(ls);
    }
}