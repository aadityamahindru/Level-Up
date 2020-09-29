// https://www.hackerrank.com/challenges/journey-to-the-moon/problem?isFullScreen=false

public class Solution {

    // Complete the journeyToMoon function below.
    static int parr[];
    static int size[];
    static int find(int u){
        if(parr[u]==u) return u;
        return parr[u]=find(parr[u]);
    }
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int p=sc.nextInt();
        parr=new int[n];
        for(int i=0;i<n;i++){
            parr[i]=i;
        }
        size=new int[n];
        Arrays.fill(size,1);
        for(int i=0;i<p;i++){
            int u =sc.nextInt();
            int v= sc.nextInt();
            int p1=find(u);
            int p2=find(v);
            if(p1!=p2){
                parr[p1]=p2;
                size[p2]+=size[p1];
            }
        }
        
        long res=0;
        long sum=0;
        for(int i=0;i<n;i++){
            if(parr[i]==i){
                res+=sum*size[i];
                sum+=size[i];
            }
        }
        System.out.println(res);
        
    }
}

// leetcode 815


class Solution {
    public int numBusesToDestination(int[][] routes, int src, int dest) {
        int n=routes.length;
        HashMap<Integer,ArrayList<Integer>> busesTostand=new HashMap<>();
        boolean busVis[]=new boolean[n];
        HashSet<Integer> busStandVis=new HashSet<>();
        for(int i=0;i<n;i++){
            for(int ele:routes[i]){
                busesTostand.putIfAbsent(ele,new ArrayList<Integer>());
                busesTostand.get(ele).add(i);
            }
        }
        ArrayDeque<Integer> q=new ArrayDeque<>();
        q.add(src);
        busStandVis.add(src);
        int count=0;
        while(q.size()>0){
            int size=q.size();
            while(size-->0){
                int busStand=q.remove();
                if(busStand==dest) return count;
                for(int bus:busesTostand.get(busStand)){
                    if(busVis[bus]) continue;
                    for(int bs:routes[bus]){
                        if(!busStandVis.contains(bs)){
                            busStandVis.add(bs);
                            q.add(bs);
                        }
                    }
                    busVis[bus]=true;
                }
            }
            count++;
        }
        return -1;
    }
}


// leetcode 1192


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