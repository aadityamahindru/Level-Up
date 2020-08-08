class Nqueens1{
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        int n=3;
        int box=6;
        //System.out.println(nQueensPermutation(n,0,0,"",new boolean[box]));
        //System.out.println(nQueensCombinations(n,0,0,"",new boolean[box]));
        //System.out.println(nQueensCombinations_sub(n,0,0,"",new boolean[box]));
        System.out.println(nQueensPermutation_sub(n,0,0,"",new boolean[box]));
    }


    public static int nQueensPermutation(int n,int qno,int idx,String ans,boolean box[]){
        if(qno==n){
            System.out.println(ans);
            return 1;
        }
        int c=0;
        for(int i=0;i<box.length;i++){
            if(!box[i]){
                box[i]=true;
                c+=nQueensPermutation(n,qno+1,0,ans+"B"+(i+1)+"Q"+(qno+1)+" ",box);
                box[i]=false;
            }
        }
        return c;
    }


    public static int nQueensCombinations(int n,int qno,int idx,String ans,boolean box[]){
        if(qno==n){
            System.out.println(ans);
            return 1;
        }
        int c=0;
        for(int i=idx;i<box.length;i++){
            c+=nQueensCombinations(n,qno+1,i+1,ans+"B"+(i+1)+"Q"+(qno+1)+" ",box);
        }
        return c;
    }

    //subsequence method
    public static int nQueensCombinations_sub(int n,int qno,int idx,String ans,boolean box[]){
        if(qno==n||idx==box.length){
            if(qno==n){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int c=0;
        if(qno<n){
            c+=nQueensCombinations_sub(n,qno+1,idx+1,ans+"B"+(idx+1)+"Q"+(qno+1)+" ",box);
        }
        c+=nQueensCombinations_sub(n,qno,idx+1,ans,box);
        return c;
    }
    public static int nQueensPermutation_sub(int n,int qno,int idx,String ans,boolean box[]){
        if(qno==n||idx==box.length){
            if(qno==n){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int c=0;
        if(qno<n&&!box[idx]){
            box[idx]=true;
            c+=nQueensPermutation_sub(n,qno+1,0,ans+"B"+(idx+1)+"Q"+(qno+1)+" ",box);
            box[idx]=false;
        }
        c+=nQueensPermutation_sub(n,qno,idx+1,ans,box);
        return c;
    }
}