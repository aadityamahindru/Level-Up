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
}