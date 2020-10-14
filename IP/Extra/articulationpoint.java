class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
       
        ArrayList<Integer> graph[]=new ArrayList[n];
        for(int i=0;i<n;i++) graph[i]=new ArrayList<>();
        
        for(List<Integer> arr:connections){
            int u=arr.get(0);
            int v=arr.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        
        List<List<Integer>> ans=new ArrayList<>();
        ap_bridges(graph,n,ans);
        return ans;
    }
    int low[];
    int dist[];
    int time=0;
    int rootCount=0;
    boolean vis[];
    public void ap_bridges(ArrayList<Integer> graph[],int n,List<List<Integer>> ans){
        low=new int[n];
        vis=new boolean[n];
        dist=new int[n];
        dfs(0,-1,graph,ans);
    }
    public void dfs(int src,int par,ArrayList<Integer> graph[],List<List<Integer>> ans){
        low[src]=dist[src]=time++;
        vis[src]=true;
        for(int e:graph[src]){
            if(!vis[e]){
                dfs(e,src,graph,ans);
                if(dist[src]<low[e]){
                    List<Integer> sans=new ArrayList<>();
                    sans.add(src);
                    sans.add(e);
                    ans.add(sans);
                }
                low[src]=Math.min(low[src],low[e]);
            }else if(e!=par){
                low[src]=Math.min(dist[e],low[src]);
            }
        }
    }
}