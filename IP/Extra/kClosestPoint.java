class Solution {
    class Pair implements Comparable<Pair>{
        int x;
        int y;
        int dist;
        Pair(int x,int y,int dist){
            this.x=x;
            this.y=y;
            this.dist=dist;
        }
        public int compareTo(Pair o){
            return (this.dist-o.dist);
        }
    }
    public int[][] kClosest(int[][] points, int K) {
        int n=points.length;
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        for(int i=0;i<n;i++){
            int x=points[i][0];
            int y=points[i][1];
            int dist=(x*x+y*y);
            pq.add(new Pair(x,y,dist));
        }
        int ans[][]=new int[K][2];
        for(int i=0;i<K;i++){
            Pair rm=pq.remove();
            ans[i][0]=rm.x;
            ans[i][1]=rm.y;
        }
        return ans;
    }
}