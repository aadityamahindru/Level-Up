class heapQuestions{
    // leet 215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int val:nums){
            pq.add(val);
            if(pq.size()>k) pq.remove();
        }
        return pq.peek();
    }

    // 378
    public int kthSmallest(int[][] matrix, int k) {
        int n=matrix.length;
        int m=matrix[0].length;
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
            return matrix[a/m][a%m]-matrix[b/m][b%m];
        });
        for(int i=0;i<n;i++) pq.add(i*m+0);
        while(--k>0){
            int idx=pq.poll();
            int r=idx/m;
            int c=idx%m;
            c++;
            if(c<m) pq.add(r*m+c);
        }
        int idx=pq.remove();
        return matrix[idx/m][idx%m];
    }
    //https://practice.geeksforgeeks.org/problems/nearly-sorted-algorithm/0

    public static void sortKsorted(int arr[],int n,int k){
	    PriorityQueue<Integer> pq=new PriorityQueue<>();
	    for(int i=0;i<k;i++){
	        pq.add(arr[i]);
	    }
	    int idx=0;
	    for(int i=k;i<n;i++){
	        pq.add(arr[i]);
	        arr[idx++]=pq.poll();
	    }
	    while(pq.size()>0){
	        arr[idx++]=pq.poll();
	    }
	    for(int val:arr) System.out.print(val+" ");
	    System.out.println();
    }
    
    // 973
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
            return (o.dist-this.dist);
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
            if(pq.size()>K) pq.poll();
        }
        int ans[][]=new int[K][2];
        for(int i=0;i<K;i++){
            Pair rm=pq.remove();
            ans[i][0]=rm.x;
            ans[i][1]=rm.y;
        }
        return ans;
    }
    // trapping rain water 2    407
    public int trapRainWater(int[][] arr) {
        if(arr.length==0||arr[0].length==0) return 0;
        int n=arr.length;
        int m=arr[0].length;
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
            return arr[a/m][a%m]-arr[b/m][b%m];
        });
        boolean vis[][]=new boolean[n][m];
        int ans=0;
        int bound=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0||i==n-1||j==0||j==m-1){
                    pq.add(i*m+j);
                    vis[i][j]=true;
                }
            }
        }
        int dir[][]={{1,0},{-1,0},{0,1},{0,-1}};
        while(pq.size()>0){
            int idx=pq.remove();
            int r=idx/m;
            int c=idx%m;
            bound=Math.max(bound,arr[r][c]);
            if(arr[r][c]<bound) ans+=bound-arr[r][c];
            for(int d=0;d<4;d++){
                int x=r+dir[d][0];
                int y=c+dir[d][1];
                if(x>=0&&y>=0&&x<n&&y<m&& !vis[x][y]){
                    vis[x][y]=true;
                    pq.add(x*m+y);
                }
            }
        }
        return ans;
    }
}