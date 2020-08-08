class Nqueens1{
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        int n=3;
        int box=6;
        //System.out.println(nQueensPermutation(n,0,box,"",new boolean[box]));
        System.out.println(nQueensCombinations(n,0,0,box,"",new boolean[box]));
    }
    public static int nQueensPermutation(int n,int qno,int box,String ans,boolean vis[]){
        if(qno==n){
            System.out.println(ans);
            return 1;
        }
        int c=0;
        for(int i=0;i<box;i++){
            if(!vis[i]){
                vis[i]=true;
                c+=nQueensPermutation(n,qno+1,box,ans+"B"+(i+1)+"Q"+(qno+1)+" ",vis);
                vis[i]=false;
            }
        }
        return c;
    }
    public static int nQueensCombinations(int n,int qno,int idx,int box,String ans,boolean vis[]){
        if(qno==n){
            System.out.println(ans);
            return 1;
        }
        int c=0;
        for(int i=idx;i<box;i++){
            if(!vis[i]){
                vis[i]=true;
                c+=nQueensCombinations(n,qno+1,i+1,box,ans+"B"+(i+1)+"Q"+(qno+1)+" ",vis);
                vis[i]=false;
            }
        }
        return c;
    }
}