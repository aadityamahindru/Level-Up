class Directions{
    public static void main(String[] args) {
        int dir[][]={{0,-1},{-1,0},{0,1},{1,0}};
        String dirS[]={"L","U","R","D"};
        System.out.println(path(0,0,3,3,"",new boolean[4][4],dir,dirS));
    }
    static int path(int sr,int sc,int er,int ec,String ans,boolean visited[][],int dir[][],String dirS[]){
      if(sr==er&&sc==ec){
          return 1;
      }
      int count=0;
      visited[sr][sc]=true;
      for(int d=0;d<dir.length;d++){
        int r=sr+dir[d][0];
        int c=sc+dir[d][1];
        if(r>=0&&r<=er&&c>=0&&c<=ec&&!visited[r][c])
        count+=path(r,c,er,ec,ans+dirS[d],visited,dir,dirS);
      }
      visited[sr][sc]=false;
      return count;
    }
}