//695 leet code
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int ans=0;
        boolean vis[][]=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(!vis[i][j]&&grid[i][j]==1){
                    int area=gcc(grid,i,j,vis);
                    ans=Math.max(area,ans);
                }
            }
        }
        return ans;
    }
    public int gcc(int grid[][], int i,int j,boolean vis[][]){
        vis[i][j]=true;
        int c=1;
        if(i-1>=0&&grid[i-1][j]==1&&!vis[i-1][j])
            c+=gcc(grid,i-1,j,vis);
        if(j-1>=0&&grid[i][j-1]==1&&!vis[i][j-1])
            c+=gcc(grid,i,j-1,vis);
        if(i+1<grid.length&&grid[i+1][j]==1&&!vis[i+1][j])
            c+=gcc(grid,i+1,j,vis);
        if(j+1<grid[0].length&&grid[i][j+1]==1&&!vis[i][j+1])
            c+=gcc(grid,i,j+1,vis);
        return c; 
    }
}


// 200 leetcode
class Solution {
    public int numIslands(char[][] area) {
        if(area.length==0)
            return 0;
        boolean visited[][]=new boolean[area.length][area[0].length];
        int size=0;
        for(int i=0;i<area.length;i++){
            for(int j=0;j<area[0].length;j++){
                if(!visited[i][j]&&area[i][j]=='1'){
                    gcc(area,i,j,visited);
                    size++;
                }
            }
        }
        return size;
    }
    public void gcc(char area[][],int x,int y,boolean visited[][]){
        visited[x][y]=true;
        if(x-1>=0 && !visited[x-1][y]&& area[x-1][y]=='1')
            gcc(area,x-1,y,visited);
        if(y+1<area[0].length && !visited[x][y+1]&& area[x][y+1]=='1')
            gcc(area,x,y+1,visited);
        if(x+1<area.length && !visited[x+1][y]&& area[x+1][y]=='1')
            gcc(area,x+1,y,visited);
        if(y-1>=0 && !visited[x][y-1]&& area[x][y-1]=='1')
            gcc(area,x,y-1,visited);
    }
}

//  leetcode 463

class Solution {
    public int islandPerimeter(int[][] grid) {
        
       int dir[][]={{1,0},{0,1},{-1,0},{0,-1}};
        int count=0;
        int nbr=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
              if(grid[i][j]==1){
                  count++;
                  for(int d=0;d<dir.length;d++){
                      int r=i+dir[d][0];
                      int c=j+dir[d][1];
                      if(r>=0&&c>=0&&r<grid.length&&c<grid[0].length&&grid[r][c]==1){
                          nbr++;
                      }
                  }
              }
            }
        }
        return 4*count-nbr;
    }       
}

// leetcode 130

class Solution {
    public void solve(char[][] board) {
        if(board.length==0||board[0].length==0) return;
        int dir[][]={{0,1},{1,0},{0,-1},{-1,0}};
        int n=board.length;
        int m=board[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0||i==n-1||j==0||j==m-1){
                    if(board[i][j]=='O'){
                        dfs(board,i,j,dir);
                    }
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]=='O') board[i][j]='X';
                else if(board[i][j]=='#') board[i][j]='O';
            }
        }
    }
    public void dfs(char board[][],int i,int j,int dir[][]){
        board[i][j]='#';
        for(int d=0;d<dir.length;d++){
            int r=i+dir[d][0];
            int c=j+dir[d][1];
            if(r>0&&c>0&&r<board.length&&c<board[0].length&&board[r][c]=='O'){
                dfs(board,r,c,dir);
            }
        }
    }
}


// 785 leetcode

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n=graph.length;
        int prevColor[]=new int[n];
        Arrays.fill(prevColor,-1);
        for(int i=0;i<n;i++){
            if(prevColor[i]==-1){
                if(!isBipartite(graph,i,n,prevColor)) return false;
            }
        }
        return true;
    }
    public boolean isBipartite(int graph[][],int src,int n,int prevColor[]){
        LinkedList<Integer> q=new LinkedList<>();
        int color=0;
        q.addLast(src);
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                int vtx=q.removeFirst();
                if(prevColor[vtx]!=-1&& prevColor[vtx]!=color) return false;
                prevColor[vtx]=color;
                for(int e:graph[vtx]){
                    if(prevColor[e]==-1){
                        q.addLast(e);
                    }
                }
            }
            color=(color+1)%2;
        }
        return true;
    }
}

// 994 leetcode

class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        int n=grid.length;
        int m=grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        ArrayDeque<int[]> q=new ArrayDeque<>();
        int freshOranges=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1) 
                    freshOranges++;
                else if(grid[i][j]==2){
                    q.add(new int[]{i,j});
                }
            }
        }
        if(freshOranges==0) return 0;
        int time=0;
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                int vtx[]=q.remove();
                int r=vtx[0];
                int c=vtx[1];
                for(int d=0;d<dir.length;d++){
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];
                    if(x>=0&&y>=0&&x<n&&y<m&&grid[x][y]==1){
                        freshOranges--;
                        grid[x][y]=2;
                        q.add(new int[]{x,y});
                    }
                }
            }
            time++;
        }
        return freshOranges==0?time-1:-1;
    }
}


// insted of q of array we use q of int and store idx 2d-->1d

class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        int n=grid.length;
        int m=grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        ArrayDeque<Integer> q=new ArrayDeque<>();
        int freshOranges=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1) 
                    freshOranges++;
                else if(grid[i][j]==2){
                    q.add(i*m+j);
                }
            }
        }
        if(freshOranges==0) return 0;
        int time=0;
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                int vtx=q.remove();
                int r=vtx/m;
                int c=vtx%m;
                for(int d=0;d<dir.length;d++){
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];
                    if(x>=0&&y>=0&&x<n&&y<m&&grid[x][y]==1){
                        freshOranges--;
                        grid[x][y]=2;
                        q.add(x*m+y);
                    }
                }
            }
            time++;
        }
        return freshOranges==0?time-1:-1;
    }
}



// topological sort

class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {
        ArrayList<Integer> graph[]=new ArrayList[n];
        for(int i=0;i<n;i++) graph[i]=new ArrayList<>();
        int indegree[]=new int[n];
        for(int[] arr:prerequisites){
            int u=arr[1];
            int v=arr[0];
            indegree[v]++;
            graph[u].add(v);
        }
        ArrayDeque<Integer> q=new ArrayDeque<>();
        for(int i=0;i<n;i++) if(indegree[i]==0) q.add(i);
        int count=0;
        while(q.size()>0){
            int vtx=q.remove();
            count++;
            for(int e:graph[vtx]){
                if(--indegree[e]==0)
                    q.add(e);
            }
        }
        return count==n;
    }
}