class unionFind{
    //leetcode 200
    class Solution {
        int parr[];
        public int find(int u){
            if(parr[u]==u) return u;
            return parr[u]=find(parr[u]);
        }
        public int numIslands(char[][] grid) {
            if(grid.length==0||grid[0].length==0) return 0;
            int n=grid.length;
            int m=grid[0].length;
            int count=0;
            parr=new int[n*m];
            for(int i=0;i<n*m;i++){
                if(grid[i/m][i%m]=='1') count++;
                parr[i]=i;
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(grid[i][j]=='1'){
                        int p1=find(i*m+j);
                        if(j+1<m&&grid[i][j+1]=='1'){
                            int p2=find(i*m+j+1);
                            if(p1!=p2){
                                parr[p2]=p1;
                                count--;
                            }
                        }
                        if(i+1<n&&grid[i+1][j]=='1'){
                            int p2=find((i+1)*m+j);
                            if(p1!=p2){
                                parr[p2]=p1;
                                count--;
                            }
                        }
                    }
                }
            }
            return count;
        }
    }




    
    // leetcode 684
    public int findParr(int u){
        if(parr[u]==u) return u;
        return parr[u]=findParr(parr[u]);
    }
    int parr[];
    public int[] findRedundantConnection(int[][] edges) {
        if(edges.length==0||edges[0].length==0)return new int[0];
        int n=edges.length;
        int ans[]=new int[2];
        parr=new int[n+1];
        for(int i=0;i<n;i++){
            parr[i]=i;
        }
        for(int[] arr:edges){
            int u=arr[0];
            int v=arr[1];
            int p1=findParr(u);
            int p2=findParr(v);
            if(p1!=p2){
                parr[p2]=p1;
            }else{
                ans[0]=u;
                ans[1]=v;
            }
        }
        return ans;
    }

    // lexographical smallest string

    /*Input: A = "parker", B = "morris", S = "parser"
    Output: "makkek"
    Explanation: Based on the equivalency information in A and B, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
    The characters in each group are equivalent and sorted in lexicographical order. So the answer is "makkek".*/ 

    public String smallestEquivalentString(String A, String B, String S) {
        parr=new int[26];
        for(int i=0;i<26;i++){
            parr[i]=i;
        }
        for(int i=0;i<A.length();i++){
            char ch1=A.charAt(i);
            char ch2=B.charAt(i);
            int p1=find(ch1-'a');
            int p2=find(ch2-'a');
            parr[p1]=Math.min(p1,p2);
            parr[p2]=Math.min(p1,p2);
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<S.length();i++){
            int ch=find(S.charAt(i)-'a');
            sb.append((char)+ch+'a');
        }
        return sb.toString();
    }
}

//547 leetcode

class Solution {
    int parr[];
    public int findParr(int u){
        if(parr[u]==u) return u;
        return parr[u]=findParr(parr[u]);
    }
    public int findCircleNum(int[][] M) {
        int n=M.length;
        parr=new int[n];
        for(int i=0;i<n;i++)
            parr[i]=i;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(M[i][j]==1&&i!=j){
                    int p1=findParr(i);
                    int p2=findParr(j);
                    if(p1!=p2)
                        parr[p2]=p1;
                }
            }
        }
        int count=0;
        for(int i=0;i<n;i++){
            if(parr[i]==i)
                count++;
        }
        return count;
    }

    // 839 leetcode

    class Solution {
        int parr[];
        public int find(int u){
            if(parr[u]==u) return u;
            return parr[u]=find(parr[u]);
        }
        public boolean isSimilar(String a,String b){
            int count=0;
            for(int i=0;i<a.length();i++){
                if(a.charAt(i)!=b.charAt(i)&&++count>2) return false;
            }
            return true;
        }
        public int numSimilarGroups(String[] A) {
            int n=A.length;
            parr=new int[n];
            for(int i=0;i<n;i++){
                parr[i]=i;
            }
            int count=n;
            for(int i=0;i<n;i++){
                int p1=find(i);
                for(int j=i+1;j<n;j++){
                    if(isSimilar(A[i],A[j])){
                        int p2=find(j);
                        if(p1!=p2){
                            parr[p2]=p1;
                            count--;
                        }
                    }       
                }
            }
            return count;
        }
    }

    // number of island 2

    int parr[];
    public int find(int u){
        if(parr[u]==u) return u;
        return parr[u]=find(parr[u]);
    }
   public List<Integer> numIslands2(int n, int m, Point[] operators) {
       // write your code here
       List<Integer> ans=new ArrayList<>();
       if(n==1&&m==1) return ans;
       parr=new int[n*m];
       for(int i=0;i<n*m;i++){
           parr[i]=i;
       }
       int mat[][]=new int[n][m];
       
       int count=0;
       int dir[][]= {{0,1},{1,0},{-1,0},{0,-1}};
       for(Point ele:operators){
           int i=ele.x;
           int j=ele.y;
           if(mat[i][j]==0){
               count++;
               mat[i][j]=1;
               int p1=find(i*m+j);
               for(int d=0;d<dir.length;d++){
                   int r=i+dir[d][0];
                   int c=j+dir[d][1];
                   if(r>=0&&c>=0&&r<n&&c<m&&mat[r][c]==1){
                       int p2=find(r*m+c);
                       if(p1!=p2){
                           parr[p2]=p1;
                           count--;
                       }
                   }
               }
           }
           ans.add(count);
       }
       return ans;
   }

   // 990 leet
   int parr[];
    public int find(int u){
        if(parr[u]==u) return u;
        return parr[u]=find(parr[u]);
    } 
    public boolean equationsPossible(String[] equations) {
        parr=new int[26];
        for(int i=0;i<26;i++) parr[i]=i;
        
        for(String s:equations){
            if(s.charAt(1)=='='){
                parr[find(s.charAt(0)-'a')]=parr[find(s.charAt(3)-'a')];
            }
        }
        for(String s:equations){
            if(s.charAt(1)=='!'){
                if(find(s.charAt(0)-'a')==find(s.charAt(3)-'a')) return false;
            }
        }
        return true;
    }

    //959 leet
    public int regionsBySlashes(String[] grid) {
        if(grid.length==0) return 0;
        int n=grid.length;
        int m=n+1;
        parr=new int[m*m];
        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                int p=i+j*m;
                parr[p]=p;
            }
        }
        int count=1;
        for(int i=0;i<n;i++){
            String s=grid[i];
            for(int j=0;j<s.length();j++){
                if(s.charAt(j)=='/'){
                    int p1=find((i+1) + j*m);
                    int p2=find(i + (j+1) *m );
                    
                    if(p1!=p2){
                        parr[p1]=Math.min(p1,p2);
                        parr[p2]=Math.min(p1,p2);
                    }else count++;
                }else if(s.charAt(j)=='\\'){
                    int p1=find(i + j*m);
                    int p2=find((i+1) + (j+1) *m );
                    
                    if(p1!=p2){
                        parr[p1]=Math.min(p1,p2);
                        parr[p2]=Math.min(p1,p2);
                    }else count++;
                }
            }
        }
        return count;
    }

    //924

    int[] parr,size;
    public int find(int u){
        if(parr[u]==u) return u;
        return parr[u]=find(parr[u]);
    }
    public int minMalwareSpread(int[][] graph, int[] initial) {
        if(graph.length==0) return 0;
        int n=graph.length;
        size=new int[n];
        parr=new int[n];
        for(int i=0;i<n;i++){
            parr[i]=i;
            size[i]=1;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(graph[i][j]==1&&i!=j){
                    int p1=find(i);
                    int p2=find(j);
                    if(p1!=p2){
                        parr[p1]=p2;
                        size[p2]+=size[p1];
                    }
                }
            }
        }
        int infectedInComponents[]=new int[n];
        Arrays.sort(initial);
        for(int i:initial){
            int p=find(i);
            infectedInComponents[p]++;
        }
        int maxPopulatedComponent =0;
        int ans=initial[0];
        for(int i:initial){
            int p=find(i);
            if(infectedInComponents[p]==1 && size[p] > maxPopulatedComponent){
                maxPopulatedComponent=size[p];
                ans=i;
            }
        }
        return ans;
    }
}