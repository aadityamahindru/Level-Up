import java.util.*;
class kosaraju{
    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }
    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v,w));
    }
    public static void display(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i < N;i++){
            sb.append(i + " -> ");
            for(Edge e: graph[i]){
                sb.append("(" + e.v + ", " + e.w +") ");
            }
            sb.append('\n');
        }

        sb.append('\n');
        System.out.println(sb.toString());
    }
    public static void topoDfs(int src,boolean vis[],ArrayList<Integer> topo){
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                topoDfs(e.v,vis,topo);
            }
        }
        topo.add(src);
    }
    public static void sccDfs(int src, ArrayList<Edge> graph[],ArrayList<Integer> ans,boolean vis[]){
        vis[src]=true;
        ans.add(src);
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                sccDfs(e.v,graph,ans,vis);
            }
        }
    }
    public static void constructGraph(){
        for(int i=0;i<N;i++)
          graph[i] = new ArrayList<>();
        
        addEdge(0,1,10);
        addEdge(1,2,10);
        addEdge(2,0,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(5,3,3);
        addEdge(4,6,8);
    }
    public static void main(String[] args) {
        constructGraph();
        ArrayList<Integer> topo=new ArrayList<>();
        boolean vis[]=new boolean[N];
        for(int i=0;i<N;i++){
            if(!vis[i]){
                topoDfs(i,vis,topo);
            }
        }
        ArrayList<Edge> ngraph[]=new ArrayList[N];
        for(int i=0;i<N;i++) ngraph[i]=new ArrayList<>();
        Arrays.fill(vis,false);
        for(int i=0;i<N;i++){
            for(Edge e:graph[i]){
                ngraph[e.v].add(new Edge(i,0));
            }
        }
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=topo.size()-1;i>=0;i--){
            int src=topo.get(i);
            if(!vis[src]){
                sccDfs(src,ngraph,ans,vis);
            }
            if(ans.size()>0)
            System.out.println(ans);
            ans.clear();
        }
    }
}