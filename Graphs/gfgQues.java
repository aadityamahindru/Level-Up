import java.util.*;
class gfgQues{
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
      int graph[][] =   {{0, 1, 1, 100, 0, 0}, 
                        {1, 0, 1, 0, 0, 0}, 
                        {1, 1, 0, 0, 0, 0},    
                        {100, 0, 0, 0, 2, 2}, 
                        {0, 0, 0, 2, 0, 2},   
                        {0, 0, 0, 2, 2, 0}}; 
        minimumCost(graph);
    }

    // https://practice.geeksforgeeks.org/problems/mother-vertex/1


    static int findMother(ArrayList<ArrayList<Integer>> g, int n)
    {
        // add your code here
        int v=0;
        boolean vis[]=new boolean[n];
        for(int i=0;i<n;i++){
           if(!vis[i]){
               dfs(i,g,vis);
               v=i;
           }
        }
        vis=new boolean[n];
        dfs(v,g,vis);
        for(boolean val:vis){
            if(!val) return -1;
        }
        return v;
    }
    static void dfs(int src,ArrayList<ArrayList<Integer>> g,boolean vis[]){
        vis[src]=true;
        for(Integer e:g.get(src)){
            if(!vis[e]){
                dfs(e,g,vis);
            }
        }
    }


    
    //https://www.geeksforgeeks.org/minimum-cost-connect-cities/
    static class prims implements Comparable<prims>{
        int u;
        int wt;
        prims(int u,int wt){
            this.u=u;
            this.wt=wt;
        }
        @Override
        public int compareTo(prims o){
            return this.wt-o.wt;
        }
    }
    public static void minimumCost(int[][] g){
        PriorityQueue<prims> pq=new PriorityQueue<>();
        int n=g.length;
        ArrayList<prims> graph[]=new ArrayList[n];
        for(int i=0;i<n;i++) graph[i]=new ArrayList<>();
        createGraph(graph,g);
        boolean vis[]=new boolean[n];
        int weight[]=new int[n];
        Arrays.fill(weight,(int)1e9);
        pq.add(new prims(0,0));
        while(pq.size()>0){
            prims vtx=pq.remove();
            if(vis[vtx.u])
                continue;
            vis[vtx.u]=true;
            for(prims e:graph[vtx.u]){
                if(!vis[e.u]&&e.wt<weight[e.u]){
                    weight[e.u]=e.wt;
                    pq.add(new prims(e.u,e.wt));
                }
            }
        }
        int ans=0;
        for(int i=0;i<n;i++){
            if(weight[i]!=(int)1e9){
                ans+=weight[i];
            }
        }
        System.out.println(ans);
    }
    public static void createGraph( ArrayList<prims> graph[],int [][] g){
        for(int i=0;i<g.length;i++){
            for(int j=0;j<g[0].length;j++){
                if(g[i][j]!=0&&i!=j){
                    addEdge(graph,i,j,g[i][j]);
                }
            }
        }
    }
    public static void addEdge(ArrayList<prims>[] graph,int u,int v,int w){
        graph[u].add(new prims(v,w));
    }
}


