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


// leetcode 1091
public int shortestPathBinaryMatrix(int[][] grid) {
    int n=grid.length;
    if(grid[0][0]==1||grid[n-1][n-1]==1) return -1;
    int dir[][]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}};
    Queue<Integer> q=new ArrayDeque<>();
    q.add(0);
    grid[0][0]=1;
    int level=0;
    while(q.size()>0){
        int size=q.size();
        while(size-->0){
            int rmv=q.remove();
            int i=rmv/n;
            int j=rmv%n;
            if(i==n-1&&j==n-1) return level+1;
            for(int d=0;d<dir.length;d++){
                int r=i+dir[d][0];
                int c=j+dir[d][1];
                if(r>=0&&c>=0&&r<n&&c<n&&grid[r][c]==0){
                    q.add(r*n+c);
                    grid[r][c]=1;
                }
            }
        }
        level++;
    }
    return -1;
}

//542 leetcode
public int[][] updateMatrix(int[][] matrix) {
    if(matrix.length==0||matrix[0].length==0) return matrix;
    int n=matrix.length;
    int m=matrix[0].length;
    int ans[][]=new int[n][m];
    for(int arr[]:ans) Arrays.fill(arr,-1);
    int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    Queue<Integer> q=new ArrayDeque<>();
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(matrix[i][j]==0){
               q.add(i*m+j);
                ans[i][j]=0;
            } 
        }
    }
    int lvl=1;
    while(q.size()>0){
        int size=q.size();
        while(size-->0){
            int vtx=q.remove();
            int i=vtx/m;
            int j=vtx%m;
            for(int d=0;d<4;d++){
                int r=i+dir[d][0];
                int c=j+dir[d][1];
                if(r>=0&&c>=0&&r<n&&c<m&&ans[r][c]==-1){
                    q.add(r*m+c);
                    ans[r][c]=lvl;
                }
            }
        }
        lvl++;
    }
    return ans;
}
// leetcode 1020

public int numEnclaves(int[][] arr) {
    int n=arr.length;
    int m=arr[0].length;
    Queue<Integer> q=new ArrayDeque<>();
    int ones=0;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            ones+=arr[i][j];
            if((i==0||j==0||i==n-1||j==m-1)&&arr[i][j]==1){
                q.add(i*m+j);
                arr[i][j]=0;
                ones--;
            }
        }
    }
    if(ones==0) return 0;
    int dir[][]={{1,0},{-1,0},{0,1},{0,-1}};
    while(q.size()>0){
        int size=q.size();
        while(size-->0){
            int rvtx=q.remove();
            int i=rvtx/m;
            int j=rvtx%m;
            for(int d=0;d<4;d++){
                int r=i+dir[d][0];
                int c=j+dir[d][1];
                if(r>=0&&c>=0&&r<n&&c<m&&arr[r][c]==1){
                    q.add(r*m+c);
                    arr[r][c]=0;
                    ones--;
                }
            }
        }
    }
    return ones;
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
// leetcode 787
class Solution {
    class Edge{
        int v;
        int w;
        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    }
    class Pair implements Comparable<Pair>{
        int vtx;
        int k;
        int wsf;
        Pair(int vtx,int wsf,int k){
            this.vtx=vtx;
            this.wsf=wsf;
            this.k=k;
        }
        public int compareTo(Pair o){
            return this.wsf-o.wsf;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<Edge> graph[]=new ArrayList[n];
        for(int i=0;i<n;i++) graph[i]=new ArrayList<>();
        for(int arr[]:flights){
            int u=arr[0];
            int v=arr[1];
            int w=arr[2];
            graph[u].add(new Edge(v,w));
        }
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        pq.add(new Pair(src,0,k+1));
        while(pq.size()>0){
            Pair rvtx=pq.remove();
            if(rvtx.k<0) continue;
            if(rvtx.vtx==dst) return rvtx.wsf;
            for(Edge e:graph[rvtx.vtx]){
                    pq.add(new Pair(e.v,rvtx.wsf+e.w,rvtx.k-1));
            }
            
        }
        return -1;
    }
}

//743 leetcode
class Solution {
    class Edge{
        int v;
        int wt;
        Edge(int v,int wt){
            this.v=v;
            this.wt=wt;
        }
    }
    class Pair implements Comparable<Pair>{
        int v;
        int wsf;
        Pair(int v,int wsf){
            this.v=v;
            this.wsf=wsf;
        }
        public int compareTo(Pair o){
            return this.wsf-o.wsf;
        }
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        ArrayList<Edge> graph[]=new ArrayList[N+1];
        for(int i=0;i<=N;i++) graph[i]=new ArrayList<>();
        for(int arr[]:times){
            int u=arr[0];
            int v=arr[1];
            int wt=arr[2];
            graph[u].add(new Edge(v,wt));
        }
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        int dist[]=new int[N+1];
        Arrays.fill(dist,(int)1e9);
        pq.add(new Pair(K,0));
        dist[K]=0;
        while(pq.size()>0){
            int size=pq.size();
            while(size-->0){
                Pair temp=pq.remove();
                for(Edge e:graph[temp.v]){
                    if(e.wt+temp.wsf<dist[e.v]){
                        dist[e.v]=e.wt+temp.wsf;
                        pq.add(new Pair(e.v,e.wt+temp.wsf));
                    }
                }
            }
        }
        int ans=(int)-1e8;
        for(int i=1;i<=N;i++){
            if(dist[i]==(int)1e9) return -1;
            ans=Math.max(ans,dist[i]);
        }
        return ans;
    }

    // 778 leetcode
    public int swimInWater(int[][] grid) {
        if(grid.length==0||grid[0].length==0) return 0;
        int n=grid.length;
        int m=grid[0].length;
        int dir[][]={{0,1},{0,-1},{1,0},{-1,0}};
        boolean vis[][]=new boolean[n][m];
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
           return grid[a/m][a%m]-grid[b/m][b%m];
        });
        pq.add(0);
        int maxTime=0;
        vis[0][0]=true;
        while(pq.size()>0){
            int idx=pq.remove();
            int i=idx/m;
            int j=idx%m;
            vis[i][j]=true;
            maxTime=Math.max(maxTime,grid[i][j]);
            if(i==n-1&&j==m-1) return maxTime;
            for(int d=0;d<dir.length;d++){
                int r=i+dir[d][0];
                int c=j+dir[d][1];
                if(r>=0&&c>=0&&r<n&&c<m&& !vis[r][c]){
                    vis[r][c]=true;
                    pq.add(r*m+c);
                }
            }
        }
        return -1;
    }
    //802
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n=graph.length;
        int vis[]=new int[n];
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(vis[i]==1) continue;
            if(vis[i]==2 || !isCyclic(graph,i,vis))
                ans.add(i);
        }
        return ans;
    }
    public boolean isCyclic(int graph[][],int src,int vis[]){
        vis[src]=1;
        for(int ele:graph[src]){
            if(vis[ele]==0){
                if(isCyclic(graph,ele,vis)) return true;   
            }
            else if(vis[ele]==1) return true;
        }
        vis[src]=2;
        return false;
    }
}