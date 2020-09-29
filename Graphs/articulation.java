class articulation{
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
        graph[v].add(new Edge(u,w));
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


    public static int findEdge(int u,int v){
        int idx = -1;
        for(int i=0;i<graph[u].size();i++){
            Edge e = graph[u].get(i);
            if(e.v == v){
                idx = i;
                break;
            }
        }

        return idx;
    }

    public static void removeEdge(int u,int v){
        int idx = findEdge(u , v);
        graph[u].remove(idx);

        idx = findEdge(v , u);
        graph[v].remove(idx);
    }

    public static void removeVtx(int u){
        while(graph[u].size()!=0){
            Edge e = graph[u].get(graph[u].size()-1);
            removeEdge(u,e.v);
        }
    }
    public static void constructGraph(){
        for(int i=0;i<N;i++)
          graph[i] = new ArrayList<>();
        
        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(5,6,3);
        addEdge(4,6,8);
        display();
    }
    static int low[];
    static int dist[];
    static boolean ap[];
    static int time=0;
    static int rootCount=0;
    static boolean vis[];
    public static void dfs_ap(int src,int par){
        vis[src]=true;
        low[src]=dist[src]=time++;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                dfs_ap(e.v,src);
            }else{
                if(e.v!=par){
                    low[e.v]=Math.min
                }
            }
        }
    }
    public static void articulation_bridges(){
        low=new int[N];
        dist=new int[N];
        ap=new boolean[N];
        vis=new boolean[N];
        for(int i=0;i<N;i++){
            if(!vis[i]){
                dfs_ap(i,-1);
                if(rootCount==1);
                ap[i]=false;
                rootCount=0;
                time=0;
            }
        }

    }
    public static void solve(){
        constructGraph();
        articulation_bridges();
    }
    public static void main(String[] args) {
        solve();
    }
}